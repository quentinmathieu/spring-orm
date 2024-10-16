CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE if exists public.account CASCADE;

CREATE TABLE user (
	id uuid DEFAULT gen_random_uuid,
	first_name VARCHAR(100),
	last_name VARCHAR(50),
	email VARCHAR(50),
	birthdate date,
	creationTime timestamp,
	balance bigint
);

first_name, last_name, to_date(birthdate, 'YYYY-MM-DD'), gen_random_uuid(), email
CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(50),
	email VARCHAR(50),
	birthday date,
	creationTime timestamp,
	balance bigint
);
