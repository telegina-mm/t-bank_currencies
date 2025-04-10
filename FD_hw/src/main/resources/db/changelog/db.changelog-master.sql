--liquibase formatted sql

-- ChangeSet 1: Создание таблицы currency
CREATE TABLE currency (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    base_currency VARCHAR(10) NOT NULL DEFAULT 'RUB',
    price_change_range VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

-- ChangeSet 2: Добавление индекса на поле name
CREATE INDEX idx_currency_name ON currency (name);

-- ChangeSet 3: Добавление начальных данных
INSERT INTO currency (id, name, base_currency, price_change_range, description) VALUES
('e1a1c5b0-1c3b-4e5d-8f3a-2b9b1c5b0e5d', 'US Dollar', 'RUB', '1.00-1.10', 'United States Dollar'),
('f2b2d6c1-2d4c-5f6e-9g4b-3c0c2d6c1f6e', 'Euro', 'RUB', '0.85-0.95', 'European Union Euro');