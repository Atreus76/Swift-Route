CREATE TABLE routes (
    id UUID PRIMARY KEY,
    driver_id UUID,
    vehicle_id UUID,
    status VARCHAR(30) NOT NULL,
    planned_date TIMESTAMP NOT NULL
);

CREATE INDEX idx_route_driver ON routes(driver_id);
CREATE INDEX idx_route_vehicle ON routes(vehicle_id);

CREATE TABLE route_stops (
    id UUID PRIMARY KEY,
    route_id UUID NOT NULL REFERENCES routes(id),
    order_id UUID NOT NULL REFERENCES orders(id),
    stop_sequence INT NOT NULL,
    eta TIMESTAMP,
    status VARCHAR(30) NOT NULL
);

CREATE INDEX idx_route_stops_route ON route_stops(route_id);
CREATE INDEX idx_route_stops_order ON route_stops(order_id);

CREATE TABLE drivers (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    license_number VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    status VARCHAR(30) NOT NULL
);

CREATE TABLE vehicles (
    id UUID PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    license_plate VARCHAR(20) NOT NULL,
    status VARCHAR(30) NOT NULL,
    weight_kg   NUMERIC(8,3) NOT NULL,
    volume_m3   NUMERIC(8,4) NOT NULL
);