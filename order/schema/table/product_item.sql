CREATE TABLE product_item(
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    order_id NUMBER NOT NULL,
    product_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL,
    PRIMARY KEY(id)
);