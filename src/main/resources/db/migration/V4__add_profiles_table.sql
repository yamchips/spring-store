CREATE TABLE store.profiles (
	id BIGINT NOT NULL,
	bio TEXT NULL,
	phone_number varchar(15) NULL,
	date_of_birth DATE NULL,
	loyalty_points INT UNSIGNED DEFAULT 0 NULL,
	CONSTRAINT profiles_pk PRIMARY KEY (id),
	CONSTRAINT profiles_users_FK FOREIGN KEY (id) REFERENCES store.users(id)
)