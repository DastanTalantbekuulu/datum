CREATE TABLE role
(
    role             VARCHAR(50) PRIMARY KEY,
    i18n             JSONB       NOT NULL DEFAULT '{}',
    description_i18n JSONB       NOT NULL DEFAULT '{}',

    created_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at       TIMESTAMPTZ
);

CREATE TRIGGER trg_role_updated
    BEFORE UPDATE
    ON role
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();
