
/* ==========================================================================
   3. ОРГАНИЗАЦИЯ И HR (Organization & HR)
   ========================================================================== */

CREATE TABLE clinic
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address_id BIGINT REFERENCES address (id),

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE clinic_name
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    clinic_id  BIGINT       NOT NULL REFERENCES clinic (id),
    language   CHAR(2)      NOT NULL REFERENCES language (language),
    name       VARCHAR(255) NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id),
    UNIQUE (clinic_id, language)
);

CREATE TABLE department
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    clinic_id  BIGINT NOT NULL REFERENCES clinic (id),

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE department_name
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    department_id BIGINT       NOT NULL REFERENCES department (id),
    language      CHAR(2)      NOT NULL REFERENCES language (language),
    name          VARCHAR(255) NOT NULL,

    created_at    TIMESTAMPTZ DEFAULT NOW(),
    created_by    BIGINT REFERENCES users (id),
    updated_at    TIMESTAMPTZ,
    updated_by    BIGINT REFERENCES users (id),
    deleted_at    TIMESTAMPTZ,
    deleted_by    BIGINT REFERENCES users (id),
    UNIQUE (department_id, language)
);

CREATE TABLE job_position
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE job_position_name
(
    id              BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    job_position_id BIGINT       NOT NULL REFERENCES job_position (id),
    language        CHAR(2)      NOT NULL REFERENCES language (language),
    name            VARCHAR(255) NOT NULL, -- 'Врач', 'Doctor'

    created_at      TIMESTAMPTZ DEFAULT NOW(),
    created_by      BIGINT REFERENCES users (id),
    updated_at      TIMESTAMPTZ,
    updated_by      BIGINT REFERENCES users (id),
    deleted_at      TIMESTAMPTZ,
    deleted_by      BIGINT REFERENCES users (id),
    UNIQUE (job_position_id, language)
);

/* ==========================================================================
   4. ЛЮДИ И СОТРУДНИКИ (People & Employees)
   ========================================================================== */

CREATE TABLE employee
(
    id            BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id     BIGINT NOT NULL REFERENCES person (id),
    user_id       BIGINT REFERENCES users (id),
    position_id   BIGINT REFERENCES job_position (id),
    department_id BIGINT REFERENCES department (id),
    hired_date    DATE,
    fired_date    DATE,

    created_at    TIMESTAMPTZ DEFAULT NOW(),
    created_by    BIGINT REFERENCES users (id),
    updated_at    TIMESTAMPTZ,
    updated_by    BIGINT REFERENCES users (id),
    deleted_at    TIMESTAMPTZ,
    deleted_by    BIGINT REFERENCES users (id)
);

CREATE TABLE contact_info
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id  BIGINT       NOT NULL REFERENCES person (id),
    type       VARCHAR(20) CHECK (type IN ('PHONE', 'EMAIL', 'WHATSAPP', 'TELEGRAM')),
    value      VARCHAR(255) NOT NULL,
    is_primary BOOLEAN     DEFAULT FALSE,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

/* ==========================================================================
   5. МЕДИЦИНСКИЙ КАТАЛОГ (Medical Catalog)
   ========================================================================== */

CREATE TABLE service_category
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    parent_id  BIGINT REFERENCES service_category (id),

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE service_category_name
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    category_id BIGINT       NOT NULL REFERENCES service_category (id),
    language    CHAR(2)      NOT NULL REFERENCES language (language),
    name        VARCHAR(255) NOT NULL,

    created_at  TIMESTAMPTZ DEFAULT NOW(),
    created_by  BIGINT REFERENCES users (id),
    updated_at  TIMESTAMPTZ,
    updated_by  BIGINT REFERENCES users (id),
    deleted_at  TIMESTAMPTZ,
    deleted_by  BIGINT REFERENCES users (id),
    UNIQUE (category_id, language)
);

CREATE TABLE service
(
    id               BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sku              VARCHAR(50) UNIQUE,
    category_id      BIGINT REFERENCES service_category (id),
    duration_minutes INT         DEFAULT 30,

    created_at       TIMESTAMPTZ DEFAULT NOW(),
    created_by       BIGINT REFERENCES users (id),
    updated_at       TIMESTAMPTZ,
    updated_by       BIGINT REFERENCES users (id),
    deleted_at       TIMESTAMPTZ,
    deleted_by       BIGINT REFERENCES users (id)
);

CREATE TABLE service_name
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    service_id BIGINT       NOT NULL REFERENCES service (id),
    language   CHAR(2)      NOT NULL REFERENCES language (language),
    name       VARCHAR(255) NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id),
    UNIQUE (service_id, language)
);

CREATE TABLE icd_version
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(50),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE icd
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    version_id BIGINT      NOT NULL REFERENCES icd_version (id),
    code       VARCHAR(20) NOT NULL,
    parent_id  BIGINT REFERENCES icd (id),

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE icd_name
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    icd_id     BIGINT       NOT NULL REFERENCES icd (id),
    language   CHAR(2)      NOT NULL REFERENCES language (language),
    name       VARCHAR(500) NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id),
    UNIQUE (icd_id, language)
);

/* ==========================================================================
   6. ЛЕЧЕБНЫЙ ПРОЦЕСС (Clinical Workflow)
   ========================================================================== */

CREATE TABLE appointment_status
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    code       VARCHAR(50) UNIQUE NOT NULL, -- DRAFT, BOOKED

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE appointment_status_name
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    status_id  BIGINT       NOT NULL REFERENCES appointment_status (id),
    language   CHAR(2)      NOT NULL REFERENCES language (language),
    name       VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id),
    UNIQUE (status_id, language)
);

CREATE TABLE appointment
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    person_id   BIGINT      NOT NULL REFERENCES person (id),
    employee_id BIGINT      NOT NULL REFERENCES employee (id),
    status_id   BIGINT      NOT NULL REFERENCES appointment_status (id),
    start_time  TIMESTAMPTZ NOT NULL,
    end_time    TIMESTAMPTZ NOT NULL,

    created_at  TIMESTAMPTZ DEFAULT NOW(),
    created_by  BIGINT REFERENCES users (id),
    updated_at  TIMESTAMPTZ,
    updated_by  BIGINT REFERENCES users (id),
    deleted_at  TIMESTAMPTZ,
    deleted_by  BIGINT REFERENCES users (id)
);

CREATE TABLE treatment
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    appointment_id BIGINT REFERENCES appointment (id),
    person_id      BIGINT NOT NULL REFERENCES person (id),
    doctor_id      BIGINT NOT NULL REFERENCES employee (id),
    clinical_data  JSONB, -- Анкеты, формулы
    notes          TEXT,

    created_at     TIMESTAMPTZ DEFAULT NOW(),
    created_by     BIGINT REFERENCES users (id),
    updated_at     TIMESTAMPTZ,
    updated_by     BIGINT REFERENCES users (id),
    deleted_at     TIMESTAMPTZ,
    deleted_by     BIGINT REFERENCES users (id)
);

CREATE TABLE diagnosis
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    treatment_id BIGINT NOT NULL REFERENCES treatment (id),
    icd_id       BIGINT NOT NULL REFERENCES icd (id),
    type         VARCHAR(20) DEFAULT 'PRIMARY',

    created_at   TIMESTAMPTZ DEFAULT NOW(),
    created_by   BIGINT REFERENCES users (id),
    updated_at   TIMESTAMPTZ,
    updated_by   BIGINT REFERENCES users (id),
    deleted_at   TIMESTAMPTZ,
    deleted_by   BIGINT REFERENCES users (id)
);

CREATE TABLE treatment_item
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    treatment_id BIGINT NOT NULL REFERENCES treatment (id),
    service_id   BIGINT NOT NULL REFERENCES service (id),
    quantity     INT         DEFAULT 1,
    tooth_number VARCHAR(5),
    base_price   DECIMAL(19, 4),

    created_at   TIMESTAMPTZ DEFAULT NOW(),
    created_by   BIGINT REFERENCES users (id),
    updated_at   TIMESTAMPTZ,
    updated_by   BIGINT REFERENCES users (id),
    deleted_at   TIMESTAMPTZ,
    deleted_by   BIGINT REFERENCES users (id)
);

/* ==========================================================================
   7. ФИНАНСЫ (Finance)
   ========================================================================== */

CREATE TABLE currency
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    iso_code   CHAR(3) UNIQUE NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE price
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    service_id  BIGINT         NOT NULL REFERENCES service (id),
    currency_id BIGINT         NOT NULL REFERENCES currency (id),
    amount      DECIMAL(19, 4) NOT NULL,
    active_from TIMESTAMPTZ DEFAULT NOW(),
    active_to   TIMESTAMPTZ,

    created_at  TIMESTAMPTZ DEFAULT NOW(),
    created_by  BIGINT REFERENCES users (id),
    updated_at  TIMESTAMPTZ,
    updated_by  BIGINT REFERENCES users (id),
    deleted_at  TIMESTAMPTZ,
    deleted_by  BIGINT REFERENCES users (id)
);

CREATE TABLE invoice_status
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    code       VARCHAR(50) UNIQUE NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE invoice_status_name
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    status_id  BIGINT       NOT NULL REFERENCES invoice_status (id),
    language   CHAR(2)      NOT NULL REFERENCES language (language),
    name       VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id),
    UNIQUE (status_id, language)
);

CREATE TABLE payment_method
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    code       VARCHAR(50) UNIQUE NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id)
);

CREATE TABLE payment_method_name
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    method_id  BIGINT       NOT NULL REFERENCES payment_method (id),
    language   CHAR(2)      NOT NULL REFERENCES language (language),
    name       VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ DEFAULT NOW(),
    created_by BIGINT REFERENCES users (id),
    updated_at TIMESTAMPTZ,
    updated_by BIGINT REFERENCES users (id),
    deleted_at TIMESTAMPTZ,
    deleted_by BIGINT REFERENCES users (id),
    UNIQUE (method_id, language)
);

CREATE TABLE invoice
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    invoice_number VARCHAR(50) UNIQUE NOT NULL,
    person_id      BIGINT             NOT NULL REFERENCES person (id),
    treatment_id   BIGINT REFERENCES treatment (id),
    status_id      BIGINT             NOT NULL REFERENCES invoice_status (id),
    currency_id    BIGINT             NOT NULL REFERENCES currency (id),
    issued_at      TIMESTAMPTZ,

    created_at     TIMESTAMPTZ DEFAULT NOW(),
    created_by     BIGINT REFERENCES users (id),
    updated_at     TIMESTAMPTZ,
    updated_by     BIGINT REFERENCES users (id),
    deleted_at     TIMESTAMPTZ,
    deleted_by     BIGINT REFERENCES users (id)
);

CREATE TABLE invoice_item
(
    id                BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    invoice_id        BIGINT         NOT NULL REFERENCES invoice (id),
    service_id        BIGINT REFERENCES service (id),
    treatment_item_id BIGINT REFERENCES treatment_item (id),

    name              VARCHAR(255), -- Фиксированное название
    quantity          DECIMAL(10, 2) NOT NULL DEFAULT 1,
    price             DECIMAL(19, 4) NOT NULL,
    discount_percent  DECIMAL(5, 2)           DEFAULT 0,

    created_at        TIMESTAMPTZ             DEFAULT NOW(),
    created_by        BIGINT REFERENCES users (id),
    updated_at        TIMESTAMPTZ,
    updated_by        BIGINT REFERENCES users (id),
    deleted_at        TIMESTAMPTZ,
    deleted_by        BIGINT REFERENCES users (id)
);

CREATE TABLE payment
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    invoice_id     BIGINT         NOT NULL REFERENCES invoice (id),
    method_id      BIGINT         NOT NULL REFERENCES payment_method (id),
    amount         DECIMAL(19, 4) NOT NULL,
    transaction_id VARCHAR(255),
    paid_at        TIMESTAMPTZ DEFAULT NOW(),

    created_at     TIMESTAMPTZ DEFAULT NOW(),
    created_by     BIGINT REFERENCES users (id),
    updated_at     TIMESTAMPTZ,
    updated_by     BIGINT REFERENCES users (id),
    deleted_at     TIMESTAMPTZ,
    deleted_by     BIGINT REFERENCES users (id)
);
