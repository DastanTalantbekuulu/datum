CREATE TABLE language
(
    language   CHAR(2) PRIMARY KEY,  -- ISO 639-1: 'ru', 'en'
    native     VARCHAR(50) NOT NULL, -- 'Русский', 'English'
    i18n JSONB NOT NULL DEFAULT '{}',

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

-- Comments
COMMENT ON TABLE language IS 'Registry of supported system languages (ISO 639-1). Used for UI localization.';

COMMENT ON COLUMN language.language IS 'ISO 639-1 two-letter code (e.g., "en", "ru"). Primary Key.';

COMMENT ON COLUMN language.native IS 'Language name in its own script (e.g., "Русский", "Deutsch"). Used for language switchers.';

-- Trigger
CREATE TRIGGER trg_language_set_updated
    BEFORE UPDATE
    ON language
    FOR EACH ROW
EXECUTE FUNCTION trigger_set_updated();
