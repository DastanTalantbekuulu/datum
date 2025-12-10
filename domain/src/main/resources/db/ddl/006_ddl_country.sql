CREATE TABLE country
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    iso_alpha2 CHAR(2)     NOT NULL,
    iso_alpha3 CHAR(3)     NOT NULL,
    is_active  BOOLEAN     NOT NULL DEFAULT TRUE,
    i18n       JSONB       NOT NULL DEFAULT '{}',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX idx_country_iso2_unique ON country (iso_alpha2) WHERE deleted_at IS NULL;
CREATE UNIQUE INDEX idx_country_iso3_unique ON country (iso_alpha3) WHERE deleted_at IS NULL;

CREATE INDEX idx_country_i18n ON country USING GIN (i18n);

CREATE TRIGGER trg_country_updated
    BEFORE UPDATE
    ON country
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE TABLE phone_code
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    country_id BIGINT      NOT NULL REFERENCES country (id) ON DELETE CASCADE,

    prefix     VARCHAR(10) NOT NULL, -- '996', '7', '1'
    mask       VARCHAR(50),          -- '(###) ##-##-##' (для фронтенда)
    emoji      VARCHAR(5),           -- '🇰🇬', '🇷🇺'

    regex      VARCHAR(255),
    is_main    BOOLEAN     DEFAULT TRUE,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

CREATE INDEX idx_country_phone_code_prefix ON phone_code (prefix);

COMMENT ON TABLE phone_code IS 'Telephone area code directory. Associates the area code (996) with the country (KG).';

-- CREATE TABLE phone_country
-- (
--     phone_id    BIGINT      NOT NULL REFERENCES phone (id) ON DELETE CASCADE,
--     country_id  BIGINT      NOT NULL REFERENCES country (id) ON DELETE RESTRICT,
--     code_id     BIGINT      REFERENCES phone_code (id) ON DELETE SET NULL,
--     created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
--     PRIMARY KEY (phone_id)
-- );
-- COMMENT ON TABLE phone_country IS 'The phones specific country of origin. This field is filled in automatically during registration.';

CREATE TABLE region
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    country_id BIGINT      NOT NULL REFERENCES country (id) ON DELETE CASCADE,
    code       VARCHAR(20),
    i18n       JSONB       NOT NULL DEFAULT '{}',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);
CREATE TRIGGER trg_region_updated
    BEFORE UPDATE
    ON region
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE TABLE city
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    country_id BIGINT      NOT NULL REFERENCES country (id) ON DELETE RESTRICT,
    region_id  BIGINT      REFERENCES region (id) ON DELETE SET NULL,
    latitude   DECIMAL(10, 8),
    longitude  DECIMAL(11, 8),
    is_active  BOOLEAN     NOT NULL DEFAULT TRUE,
    i18n       JSONB       NOT NULL DEFAULT '{}',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);
CREATE TRIGGER trg_city_updated
    BEFORE UPDATE
    ON city
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE INDEX idx_city_country_region ON city (country_id, region_id);

CREATE TABLE street
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    city_id       BIGINT      NOT NULL REFERENCES city (id) ON DELETE CASCADE,
    external_code VARCHAR(100),
    i18n          JSONB       NOT NULL DEFAULT '{}',
    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at    TIMESTAMPTZ
);

COMMENT ON TABLE street IS 'Реестр улиц города. Используется для строгого выбора адреса.';

CREATE TRIGGER trg_street_updated
    BEFORE UPDATE
    ON street
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE TABLE address
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,

    city_id     BIGINT      NOT NULL REFERENCES city (id) ON DELETE RESTRICT,
    street_id   BIGINT      NOT NULL REFERENCES street (id) ON DELETE RESTRICT,
    building    VARCHAR(50), -- '125/1'
    apartment   VARCHAR(20), -- 'кв 5'
    floor       VARCHAR(10),
    entrance    VARCHAR(10),
    postal_code VARCHAR(20),
    latitude    DECIMAL(10, 8),
    longitude   DECIMAL(11, 8),
    comment     VARCHAR(255),
    -- Аудит
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    created_by  BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_by  BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    deleted_at  TIMESTAMPTZ,
    deleted_by  BIGINT      REFERENCES users (id) ON DELETE SET NULL
);

CREATE TRIGGER trg_address_updated
    BEFORE UPDATE
    ON address
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();



