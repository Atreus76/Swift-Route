CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE order_lines (
    id UUID PRIMARY KEY,
    customer_id UUID FOREIGN KEY REFERENCES orders(customer_id) ON DELETE CASCADE,
    sku VARCHAR(30) NOT NULL,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    weight_kg NUMERIC(8,3) NOT NULL CHECK (weight_kg > 0),
    volumn_m3 NUMERIC(8,4) NOT NULL CHECK (volumn_m3 > 0),
    unit_price NUMERIC(12,2) NOT NULL CHECK (unit_price >= 0),
)

CREATE INDEX idx_order_lines_order ON order_lines (order_id);
