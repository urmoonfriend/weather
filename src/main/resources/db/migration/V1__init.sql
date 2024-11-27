create table tech_jusan_weather.city
(
    id         bigserial primary key,
    name       varchar(255) not null,
    latitude   DECIMAL(9, 6),
    longitude  DECIMAL(9, 6),
    timezone   VARCHAR(50),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE
);

insert into tech_jusan_weather.city(name, latitude, longitude, timezone, created_at, updated_at)
values ('Almaty', 43.25, 76.95, 'Asia/Almaty', NOW(), NOW()),
       ('New York', 40.7142, -74.0064, 'America/New_York', NOW(), NOW()),
       ('London', 51.5171, -0.1062, 'Europe/London', NOW(), NOW()),
       ('Tokio', -11.0833, -69.2667, 'America/La_Paz', NOW(), NOW());
