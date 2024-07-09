ALTER TABLE product
    ADD qty INT NULL;

ALTER TABLE product
    MODIFY qty INT NOT NULL;

ALTER TABLE st_user
    MODIFY avg_rating DOUBLE NOT NULL;

ALTER TABLE st_user
    MODIFY user_type INT NULL;