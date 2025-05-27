--liquibase formatted sql
--changeset Alexandr:initialize_db
--comment first_start

CREATE TABLE banks
(
    bank_id        BIGSERIAL PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    license_number VARCHAR(50)  NOT NULL,
    bic            VARCHAR(20)  NOT NULL,
    inn            VARCHAR(20)  NOT NULL -- ИНН банка
);

CREATE TABLE credit_contracts
(
    contract_id     BIGSERIAL PRIMARY KEY,
    client_id       INT                               NOT NULL,
    bank_id         BIGINT REFERENCES banks (bank_id) NOT NULL,
    contract_number VARCHAR(50) UNIQUE                NOT NULL,
    start_date      DATE                              NOT NULL,
    end_date        DATE                              NOT NULL,
    original_amount DECIMAL(15, 2)                    NOT NULL,
    currency        VARCHAR(3)                        NOT NULL,
    interest_rate   DECIMAL(5, 2)                     NOT NULL,
    contract_status VARCHAR(20)                       NOT NULL, -- 'ACTIVE', 'CLOSED', 'OVERDUE'
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE payment_schedule
(
    schedule_id      BIGSERIAL PRIMARY KEY,
    contract_id      BIGINT REFERENCES credit_contracts (contract_id) NOT NULL,
    payment_date     DATE                                             NOT NULL,
    payment_amount   DECIMAL(15, 2)                                   NOT NULL,
    principal_amount DECIMAL(15, 2)                                   NOT NULL,
    interest_amount  DECIMAL(15, 2)                                   NOT NULL,
    status           VARCHAR(20)                                      NOT NULL, -- 'PENDING', 'PAID', 'MISSED'
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE payments
(
    payment_id            BIGSERIAL PRIMARY KEY,
    contract_id           BIGINT REFERENCES credit_contracts (contract_id) NOT NULL,
    schedule_id           BIGINT REFERENCES payment_schedule (schedule_id),
    payment_date          TIMESTAMP                                        NOT NULL,
    amount                DECIMAL(15, 2)                                   NOT NULL,
    payment_method        VARCHAR(50)                                      NOT NULL,
    transaction_reference VARCHAR(100),
    created_at            TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE delinquency_history
(
    delinquency_id       BIGSERIAL PRIMARY KEY,
    contract_id          BIGINT REFERENCES credit_contracts (contract_id) NOT NULL,
    start_date           DATE                                             NOT NULL,
    end_date             DATE,
    days_delinquent      INT,
    max_delinquency_days INT,
    created_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);