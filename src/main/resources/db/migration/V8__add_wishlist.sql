CREATE TABLE store.wishlist (
	user_id BIGINT,
	product_id BIGINT,
	PRIMARY KEY (user_id, product_id)
);

ALTER TABLE wishlist
	ADD CONSTRAINT wishlist_fk_product FOREIGN KEY (product_id) REFERENCES store.products(id);

ALTER TABLE wishlist
	ADD CONSTRAINT wishlist_fk_user FOREIGN KEY (user_id) REFERENCES store.users(id);