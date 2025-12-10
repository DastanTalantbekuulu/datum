CREATE TABLE gender
(
    code       CHAR(1) PRIMARY KEY CHECK (code IN ('M', 'F', '<')),
    i18n JSONB NOT NULL DEFAULT '{}',
    short_i18n JSONB NOT NULL DEFAULT '{}', -- Для сокращений "М", "Ж", "F"
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX idx_gender_unique ON gender (code) WHERE deleted_at IS NULL;