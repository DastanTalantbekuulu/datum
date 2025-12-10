CREATE TABLE phone
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number       VARCHAR(20) NOT NULL,
    -- E.164 format validation (+ followed by 7-15 digits)
    CONSTRAINT phone_number_check CHECK (number ~ '^[a-zA-Z0-9.!#$%&*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$'),
    CONSTRAINT phone_number_unique UNIQUE (number),

    has_whatsapp BOOLEAN     NOT NULL DEFAULT FALSE,
    has_telegram BOOLEAN     NOT NULL DEFAULT FALSE,

    created_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at   TIMESTAMPTZ
);

COMMENT ON TABLE phone IS 'Global registry of unique phone numbers.';
COMMENT ON COLUMN phone.number IS 'Phone number in E.164 format (e.g. +1234567890). Unique system-wide.';

CREATE TRIGGER trg_phone_set_updated
    BEFORE UPDATE
    ON phone
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE TABLE email
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address    VARCHAR(255) NOT NULL,
    CONSTRAINT email_check CHECK (address ~ '^.+@.+\..+$'),
    CONSTRAINT email_unique UNIQUE (address),

    created_at TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

COMMENT ON TABLE email IS 'Global registry of unique email addresses.';

CREATE TRIGGER trg_email_set_updated
    BEFORE UPDATE
    ON email
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();

CREATE TABLE contact_type
(
    type       VARCHAR(20) PRIMARY KEY, -- 'HOME', 'WORK', 'OTHER'
    i18n       JSONB       NOT NULL DEFAULT '{}',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

COMMENT ON TABLE contact_type IS 'Dictionary of contact_type types (HOME, WORK, OTHER). Reference table.';

CREATE TABLE user_phone
(
    user_id      BIGINT      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    phone_id     BIGINT      NOT NULL REFERENCES phone (id) ON DELETE RESTRICT,
    contact_type VARCHAR(20) REFERENCES contact_type (type) ON DELETE SET NULL,

    is_primary   BOOLEAN     NOT NULL DEFAULT FALSE,
    is_verified  BOOLEAN     NOT NULL DEFAULT FALSE,

    created_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    PRIMARY KEY (user_id, phone_id)
);

COMMENT ON TABLE user_phone IS 'Links users to phones. Stores ownership attributes like verification status and type.';

CREATE UNIQUE INDEX idx_user_phone_primary ON user_phone (user_id) WHERE is_primary IS TRUE;

CREATE TABLE user_email
(
    user_id      BIGINT      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    email_id     BIGINT      NOT NULL REFERENCES email (id) ON DELETE RESTRICT,
    contact_type VARCHAR(20) REFERENCES contact_type (type) ON DELETE SET NULL,

    is_primary   BOOLEAN     NOT NULL DEFAULT FALSE,
    is_verified  BOOLEAN     NOT NULL DEFAULT FALSE,

    created_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    PRIMARY KEY (user_id, email_id)
);

COMMENT ON TABLE user_email IS 'Links users to emails. Stores ownership attributes like verification status and type.';

CREATE UNIQUE INDEX idx_user_email_primary ON user_email (user_id) WHERE is_primary IS TRUE;
