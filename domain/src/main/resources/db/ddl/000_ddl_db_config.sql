-- Database: old

-- DROP DATABASE IF EXISTS old;

CREATE DATABASE datum
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    ICU_LOCALE = 'und'
    LOCALE_PROVIDER = 'icu'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

ALTER DATABASE datum
    SET "TimeZone" TO 'UTC';