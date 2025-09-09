ALTER TABLE purchase DROP CONSTRAINT IF EXISTS purchase_product_id_fkey;

ALTER TABLE purchase DROP COLUMN product_id;

ALTER TABLE purchase ADD COLUMN product_article_id INTEGER;

ALTER TABLE product ADD CONSTRAINT product_article_id_unique UNIQUE (article_id);

ALTER TABLE purchase ADD CONSTRAINT purchase_product_article_id_fkey
    FOREIGN KEY (product_article_id) REFERENCES product(article_id);