CREATE TABLE order_lines (
    id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id    UUID         NOT NULL REFERENCES orders(id),
    sku         VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    quantity    INT          NOT NULL CHECK (quantity > 0),
    weight_kg   NUMERIC(8,3) NOT NULL,
    volume_m3   NUMERIC(8,4) NOT NULL,
    unit_price  NUMERIC(12,2) NOT NULL
);

CREATE INDEX idx_order_lines_order ON order_lines(order_id);