CREATE TABLE store.categories (
	id TINYINT auto_increment ,
	name varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE store.products (
	id BIGINT AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	category_id TINYINT,
	PRIMARY KEY (id),
	CONSTRAINT products_fk FOREIGN KEY (category_id) REFERENCES store.categories(id) ON DELETE RESTRICT
);