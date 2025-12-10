CREATE TABLE person
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id        BIGINT UNIQUE REFERENCES users (id) ON DELETE SET NULL,

    last_name      VARCHAR(100)  NOT NULL,
    first_name     VARCHAR(100)  NOT NULL,
    middle_name    VARCHAR(100),

    birth_date     DATE          NOT NULL,
    gender         CHAR(1)       NOT NULL REFERENCES gender (code),
    citizenship_id BIGINT REFERENCES country (id),

    created_at     TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    created_by     BIGINT        REFERENCES users (id) ON DELETE SET NULL,
    updated_at     TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_by     BIGINT        REFERENCES users (id) ON DELETE SET NULL,
    deleted_at     TIMESTAMPTZ,
    deleted_by     BIGINT        REFERENCES users (id) ON DELETE SET NULL
);

CREATE TRIGGER trg_person_updated
    BEFORE UPDATE
    ON person
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();


CREATE TABLE identity_document
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id           BIGINT      NOT NULL REFERENCES person (id) ON DELETE CASCADE,

    document_type       VARCHAR(2)  NOT NULL,
    doc_number          VARCHAR(50) NOT NULL,
    serial              VARCHAR(20),
    authority           VARCHAR(255),
    issue_date          DATE        NOT NULL,

    issuing_country_id  BIGINT      NOT NULL REFERENCES country (id),

    mrz_string_1        VARCHAR(44),
    mrz_string_2        VARCHAR(44),
    mrz_string_3        VARCHAR(30),

    mrz_surname         VARCHAR(100),
    mrz_given_names     VARCHAR(100),
    mrz_doc_number      VARCHAR(20),
    mrz_birth_date      DATE,
    mrz_expiry_date     DATE        NOT NULL,
    mrz_personal_number VARCHAR(20),

    mrz_sex_code        CHAR(1) REFERENCES gender (code),

    mrz_nationality_id  BIGINT REFERENCES country (id),

    is_primary          BOOLEAN     NOT NULL DEFAULT FALSE,
    is_active           BOOLEAN     NOT NULL DEFAULT TRUE,

    created_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by          BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by          BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    deleted_at          TIMESTAMPTZ,
    deleted_by          BIGINT      REFERENCES users (id) ON DELETE SET NULL,

    CONSTRAINT id_doc_unique UNIQUE (issuing_country_id, document_type, doc_number)
);

CREATE TRIGGER trg_identity_document_updated
    BEFORE UPDATE
    ON identity_document
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE UNIQUE INDEX idx_identity_doc_primary
    ON identity_document (person_id)
    WHERE is_primary IS TRUE AND deleted_at IS NULL;


CREATE TABLE storage_file
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    -- Имя файла, которое видел пользователь при загрузке ("passport_scan.jpg")
    original_name VARCHAR(255) NOT NULL,
    -- Тип содержимого (MIME-type), чтобы браузер знал, как открывать ("image/jpeg", "application/pdf")
    mime_type     VARCHAR(100) NOT NULL,
    -- Размер в байтах (полезно для статистики и UI)
    size          BIGINT       NOT NULL,
    -- S3 Bucket (или папка), где лежит файл ("avatars", "passports", "documents")
    bucket        VARCHAR(100) NOT NULL,
    -- Путь внутри бакета (Key). Например: "2025/12/06/uuid-uuid.jpg"
    -- Мы НЕ храним полный домен (https://s3.amazon...), потому что домены меняются.
    path          VARCHAR(512) NOT NULL,
    -- Публичный ли файл? (Если FALSE, то для скачивания нужна presigned link)
    is_public     BOOLEAN      NOT NULL DEFAULT FALSE,
    -- Аудит
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    created_by    BIGINT       REFERENCES users (id) ON DELETE SET NULL,
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_by    BIGINT       REFERENCES users (id) ON DELETE SET NULL,
    deleted_at    TIMESTAMPTZ,
    deleted_by    BIGINT       REFERENCES users (id) ON DELETE SET NULL,
    -- Защита от дублей по пути (в одном бакете)
    CONSTRAINT uq_storage_file_path UNIQUE (bucket, path)
);

COMMENT ON TABLE storage_file IS 'Реестр всех загруженных файлов (метаданные). Сами файлы лежат в S3/MinIO.';
COMMENT ON COLUMN storage_file.path IS 'Относительный путь (S3 Key). Без домена.';
COMMENT ON COLUMN storage_file.size IS 'Размер файла в байтах.';
-- Триггер обновления времени
CREATE TRIGGER trg_storage_file_updated
    BEFORE UPDATE
    ON storage_file
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();


CREATE TABLE photo_type
(
    code        VARCHAR(50) PRIMARY KEY, -- 'AVATAR', 'FULL_BODY', 'FACE_ISO'
    description VARCHAR(255),            -- Техническое описание (напр. "For biometric matching")
    i18n        JSONB       NOT NULL DEFAULT '{}',
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at  TIMESTAMPTZ
);

COMMENT ON TABLE photo_type IS 'Справочник типов фотографий (Аватар, Лицо для паспорта, В полный рост).';

CREATE TABLE person_photo
(
    id              BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id       BIGINT      NOT NULL REFERENCES person (id) ON DELETE CASCADE,
    storage_file_id BIGINT      NOT NULL REFERENCES storage_file (id) ON DELETE RESTRICT,
    type            VARCHAR(50) NOT NULL REFERENCES photo_type (code) ON DELETE RESTRICT,
    is_primary      BOOLEAN     NOT NULL DEFAULT FALSE,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by      BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by      BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    deleted_at      TIMESTAMPTZ,
    deleted_by      BIGINT      REFERENCES users (id) ON DELETE SET NULL
);

COMMENT ON TABLE person_photo IS 'Связь человека с файлом фотографии определенного типа.';

-- Триггер обновления времени
CREATE TRIGGER trg_person_photo_updated
    BEFORE UPDATE
    ON person_photo
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE UNIQUE INDEX idx_person_photo_primary_per_type
    ON person_photo (person_id, type)
    WHERE is_primary IS TRUE AND deleted_at IS NULL;

CREATE TABLE relation_type
(
    code       VARCHAR(50) PRIMARY KEY, -- 'FATHER', 'MOTHER', 'GUARDIAN'
    CONSTRAINT check_relation_type_code CHECK (code ~ '^[A-Z_]+$' AND length(code) BETWEEN 3 AND 50),
    i18n       JSONB       NOT NULL DEFAULT '{}',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

COMMENT ON TABLE relation_type IS 'Справочник типов родственных связей (Отец, Мать, Опекун).';

-- 2. Таблица связей
CREATE TABLE person_relation
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id     BIGINT      NOT NULL REFERENCES person (id) ON DELETE CASCADE,
    relative_id   BIGINT      NOT NULL REFERENCES person (id) ON DELETE CASCADE,
    relation_type VARCHAR(50) NOT NULL REFERENCES relation_type (code),
    is_biological BOOLEAN              DEFAULT TRUE,

    created_by    BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by    BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_by    BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    deleted_at    TIMESTAMPTZ,

    CONSTRAINT check_relation_self CHECK (person_id <> relative_id),
    UNIQUE (person_id, relative_id, relation_type)
);


