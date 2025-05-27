--liquibase formatted sql
--changeset Alexandr:load_test_data
--comment Загрузка данных для проверки

-- Insert into banks
INSERT INTO banks (name, license_number, bic, inn)
VALUES ('First Credit Bank', '1234567890', '041234567', '7701234567'),
       ('Capital Trust', '9876543210', '042345678', '7712345678'),
       ('Global Finance', '1112233445', '043456789', '7723456789');

-- Insert into credit_contracts
INSERT INTO credit_contracts (client_id, bank_id, contract_number, start_date, end_date, original_amount, currency,
                              interest_rate, contract_status)
VALUES (1, 1, 'CC1001', '2023-01-01', '2026-01-01', 500000.00, 'RUB', 12.50, 'ACTIVE'),
       (2, 2, 'CC1002', '2022-05-15', '2025-05-15', 300000.00, 'RUB', 10.00, 'ACTIVE'),
       (3, 3, 'CC1003', '2021-03-10', '2024-03-10', 450000.00, 'RUB', 11.25, 'CLOSED'),
       (4, 1, 'CC1004', '2023-08-01', '2026-08-01', 700000.00, 'RUB', 13.00, 'ACTIVE'),
       (5, 2, 'CC1005', '2022-11-20', '2025-11-20', 250000.00, 'RUB', 9.75, 'OVERDUE'),
       (6, 1, 'CC1006', '2023-04-01', '2026-04-01', 600000.00, 'RUB', 12.00, 'ACTIVE'),
       (7, 3, 'CC1007', '2021-09-15', '2024-09-15', 550000.00, 'RUB', 10.50, 'CLOSED'),
       (8, 2, 'CC1008', '2022-06-01', '2025-06-01', 400000.00, 'RUB', 11.00, 'ACTIVE'),
       (9, 3, 'CC1009', '2020-12-01', '2023-12-01', 350000.00, 'RUB', 9.50, 'CLOSED'),
       (10, 1, 'CC1010', '2023-02-01', '2026-02-01', 800000.00, 'RUB', 13.25, 'ACTIVE');

-- Insert into payment_schedule
INSERT INTO payment_schedule (contract_id, payment_date, payment_amount, principal_amount, interest_amount, status)
VALUES (1, '2024-06-01', 15000.00, 12000.00, 3000.00, 'PENDING'),
       (1, '2024-07-01', 15000.00, 12000.00, 3000.00, 'PENDING'),
       (2, '2024-06-15', 12000.00, 10000.00, 2000.00, 'PENDING'),
       (2, '2024-07-15', 12000.00, 10000.00, 2000.00, 'PENDING'),
       (3, '2024-03-10', 15000.00, 13000.00, 2000.00, 'PAID'),
       (4, '2024-06-01', 17000.00, 14000.00, 3000.00, 'PENDING'),
       (5, '2024-05-20', 10000.00, 8000.00, 2000.00, 'MISSED'),
       (6, '2024-06-01', 16000.00, 13000.00, 3000.00, 'PENDING'),
       (7, '2024-02-15', 14000.00, 11000.00, 3000.00, 'PAID'),
       (8, '2024-06-01', 13000.00, 10000.00, 3000.00, 'PENDING'),
       (9, '2023-12-01', 12000.00, 10000.00, 2000.00, 'PAID'),
       (10, '2024-06-01', 18000.00, 15000.00, 3000.00, 'PENDING');

-- Insert into payments
INSERT INTO payments (contract_id, schedule_id, payment_date, amount, payment_method, transaction_reference)
VALUES (3, 5, '2024-03-10 10:00:00', 15000.00, 'Bank Transfer', 'TXN123001'),
       (7, 9, '2024-02-15 09:30:00', 14000.00, 'Card Payment', 'TXN123002'),
       (9, 11, '2023-12-01 14:15:00', 12000.00, 'Bank Transfer', 'TXN123003'),
       (2, NULL, '2024-05-15 13:00:00', 12000.00, 'Cash', 'TXN123004'),
       (5, NULL, '2024-05-20 15:45:00', 10000.00, 'Mobile Payment', 'TXN123005'),
       (1, NULL, '2024-05-01 11:00:00', 15000.00, 'Bank Transfer', 'TXN123006'),
       (4, NULL, '2024-04-01 10:10:00', 17000.00, 'Card Payment', 'TXN123007'),
       (6, NULL, '2024-05-01 08:50:00', 16000.00, 'Bank Transfer', 'TXN123008'),
       (8, NULL, '2024-05-01 09:40:00', 13000.00, 'Cash', 'TXN123009'),
       (10, NULL, '2024-05-01 10:30:00', 18000.00, 'Mobile Payment', 'TXN123010');

-- Insert into delinquency_history
INSERT INTO delinquency_history (contract_id, start_date, end_date, days_delinquent, max_delinquency_days)
VALUES (5, '2024-04-01', '2024-04-20', 19, 19),
       (5, '2024-05-21', NULL, 6, 25),
       (2, '2023-10-01', '2023-10-10', 9, 9),
       (8, '2024-01-01', '2024-01-15', 14, 14),
       (9, '2022-07-01', '2022-07-25', 24, 24),
       (1, '2023-06-01', '2023-06-10', 9, 9),
       (4, '2024-02-01', '2024-02-10', 9, 9),
       (6, '2023-12-01', '2023-12-15', 14, 14),
       (7, '2022-01-01', '2022-01-12', 11, 11),
       (3, '2023-03-01', '2023-03-09', 8, 8);
