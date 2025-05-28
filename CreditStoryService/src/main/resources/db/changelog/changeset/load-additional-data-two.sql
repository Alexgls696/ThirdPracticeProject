--liquibase formatted sql
--changeset Alexandr:additional_data_2
--comment Новые данные для лучшего примера

-- Для пользователя 1

-- График платежей по договору CC1013 (contract_id = 13)
INSERT INTO payment_schedule (contract_id, payment_date, payment_amount, principal_amount, interest_amount, status)
VALUES
    (1, '2023-09-01', 16000.00, 13000.00, 3000.00, 'PAID'),
    (1, '2023-10-01', 16000.00, 13000.00, 3000.00, 'PAID'),
    (1, '2023-11-01', 16000.00, 13000.00, 3000.00, 'MISSED'),
    (1, '2023-12-01', 16000.00, 13000.00, 3000.00, 'PAID'),
    (1, '2024-01-01', 16000.00, 13000.00, 3000.00, 'MISSED');


-- Платежи по договору CC1013 (contract_id = 13)
INSERT INTO payments (contract_id, schedule_id, payment_date, amount, payment_method, transaction_reference)
VALUES
    (11, 1, '2023-09-01', 16000.00, 'BANK_TRANSFER', 'TRX31001'),
    (11, 2, '2023-10-01', 16000.00, 'BANK_TRANSFER', 'TRX31002'),
-- Платежа за ноябрь нет (MISSED)
    (11, 4, '2023-12-01', 16000.00, 'BANK_TRANSFER', 'TRX31003');
-- Платежа за январь также нет (MISSED)

-- Просрочка за ноябрь и январь
INSERT INTO delinquency_history (contract_id, start_date, end_date, days_delinquent, max_delinquency_days)
VALUES
    (11, '2023-11-02', '2023-11-20', 18, 18),
    (11, '2024-01-02', '2024-01-25', 23, 23);
