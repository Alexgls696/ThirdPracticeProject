--liquibase formatted sql
--changeset Alexandr:load_additional_test_data
--comment Загрузка дополнительный контрактов каждому человеку

-- Дополнительные контракты для клиентов 1, 2 и 3
INSERT INTO credit_contracts (client_id, bank_id, contract_number, start_date, end_date, original_amount, currency,
                              interest_rate, contract_status)
VALUES (1, 2, 'CC1011', '2024-01-01', '2027-01-01', 200000.00, 'RUB', 11.50, 'ACTIVE'),
       (2, 1, 'CC1012', '2023-07-15', '2026-07-15', 150000.00, 'RUB', 10.25, 'ACTIVE'),
       (3, 2, 'CC1013', '2022-02-01', '2025-02-01', 300000.00, 'RUB', 9.75, 'OVERDUE');


INSERT INTO payment_schedule (contract_id, payment_date, payment_amount, principal_amount, interest_amount, status)
VALUES
-- Contract CC1011
(11, '2024-06-01', 18000.00, 15000.00, 3000.00, 'PENDING'),
(11, '2024-07-01', 18000.00, 15000.00, 3000.00, 'PENDING'),

-- Contract CC1012
(12, '2024-05-01', 10000.00, 8500.00, 1500.00, 'PAID'),
(12, '2024-06-01', 10000.00, 8500.00, 1500.00, 'PENDING'),

-- Contract CC1013
(13, '2023-12-01', 12000.00, 10000.00, 2000.00, 'MISSED'),
(13, '2024-01-01', 12000.00, 10000.00, 2000.00, 'MISSED');


INSERT INTO payments (contract_id, schedule_id, payment_date, amount, payment_method, transaction_reference)
VALUES
-- Payment for CC1011, first schedule
(11, 1, '2024-06-01 09:00:00', 18000.00, 'BANK_TRANSFER', 'TRX10001'),

-- Payment for CC1012, first schedule
(12, 3, '2024-05-01 10:15:00', 10000.00, 'CARD_PAYMENT', 'TRX10002');

INSERT INTO delinquency_history (contract_id, start_date, end_date, days_delinquent, max_delinquency_days)
VALUES
-- Contract CC1013 — просрочка в декабре 2023
(13, '2023-12-02', '2024-01-15', 44, 44);