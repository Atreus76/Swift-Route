CREATE TABLE tracking_events(
    id UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    route_id UUID NOT NULL,
    route_stop_id UUID,
    status VARCHAR(30) NOT NULL,
    description VARCHAR(255) NOT NULL,
    time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    latitude DOUBLE,
    longtitude DOUBLE
);