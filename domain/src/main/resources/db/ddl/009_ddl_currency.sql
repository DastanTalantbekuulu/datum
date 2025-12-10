CREATE TABLE currency
(
    code       CHAR(3) PRIMARY KEY, -- ISO 4217: 'USD', 'EUR', 'KGS', 'RUB'
    symbol     VARCHAR(5) NOT NULL, -- '$', '€', 'с', '₽'
    decimals   SMALLINT   NOT NULL DEFAULT 2, -- 2 для USD/KGS, 0 для JPY
    i18n JSONB NOT NULL DEFAULT '{}',

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMPTZ
);

COMMENT ON TABLE currency IS 'Справочник валют (ISO 4217).';

-- Триггер
CREATE TRIGGER trg_currency_updated BEFORE UPDATE ON currency FOR EACH ROW EXECUTE FUNCTION trigger_set_updated();

