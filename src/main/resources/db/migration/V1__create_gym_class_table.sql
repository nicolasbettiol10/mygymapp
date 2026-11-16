CREATE TABLE gym_class (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    instructor VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    start_time TIMESTAMP NOT NULL,
    duration_minutes INTEGER NOT NULL
);
