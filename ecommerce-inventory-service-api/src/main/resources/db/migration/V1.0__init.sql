CREATE TABLE INVENTORY
(
  ID           VARCHAR(32) NOT NULL,
  JSON_CONTENT JSON        NOT NULL,
  PRODUCT_ID   VARCHAR(32) GENERATED ALWAYS AS (JSON_CONTENT ->> '$.productId') VIRTUAL,
  CREATED_AT   BIGINT GENERATED ALWAYS AS (JSON_CONTENT ->> '$.createdAt') VIRTUAL,
  PRIMARY KEY (ID)
) CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
