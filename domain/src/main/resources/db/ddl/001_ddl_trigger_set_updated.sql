CREATE OR REPLACE FUNCTION trigger_set_updated()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at := now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

COMMENT ON FUNCTION trigger_set_updated() IS
    'Trigger function that automatically updates the "updated_at" column to the current timestamp before any row update.';

