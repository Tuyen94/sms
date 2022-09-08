CREATE TABLE product_item(
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    order_id NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL,
    PRIMARY KEY(id)
);
--
CREATE TABLE orderdb.product_item(
    id BIGSERIAL PRIMARY KEY,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity integer NOT NULL
);