-- Create airports table
CREATE TABLE airports (
    id_airport BIGSERIAL PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);

-- Create planes table
CREATE TABLE planes (
    id_plane BIGSERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
  	yearMan INTEGER NOT NULL,
    capacity INTEGER NOT NULL
);

-- Create flights table
CREATE TABLE flights (
    id_flight BIGSERIAL PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL UNIQUE,
    departure_airport_id BIGINT NOT NULL,
    arrival_airport_id BIGINT NOT NULL,
    plane_id BIGINT NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    duration DOUBLE PRECISION,
    number_of_seats INTEGER NOT NULL,
    first_class_seats INTEGER,
    business_class_seats INTEGER,
    premium_seats INTEGER,
    economy_seats INTEGER,
    first_class_price DECIMAL(10,2),
    business_class_price DECIMAL(10,2),
    premium_price DECIMAL(10,2),
    economy_price DECIMAL(10,2),
    CONSTRAINT fk_departure_airport FOREIGN KEY (departure_airport_id) REFERENCES airports(id_airport),
    CONSTRAINT fk_arrival_airport FOREIGN KEY (arrival_airport_id) REFERENCES airports(id_airport),
    CONSTRAINT fk_plane FOREIGN KEY (plane_id) REFERENCES planes(id_plane)
);

-- Create indexes for better performance
CREATE INDEX idx_flights_departure_airport ON flights(departure_airport_id);
CREATE INDEX idx_flights_arrival_airport ON flights(arrival_airport_id);
CREATE INDEX idx_flights_plane ON flights(plane_id);
CREATE INDEX idx_flights_departure_time ON flights(departure_time);
CREATE INDEX idx_airports_code ON airports(code);
CREATE INDEX idx_airports_city ON airports(city);