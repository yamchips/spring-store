ALTER TABLE store.wishlist
    DROP FOREIGN KEY wishlist_fk_product;

ALTER TABLE store.wishlist
    ADD CONSTRAINT wishlist_fk_product
    FOREIGN KEY (product_id) REFERENCES store.products(id)
    ON DELETE CASCADE ON UPDATE RESTRICT;
