CREATE TABLE orders(
    order_id NUMBER GENERATED ALWAYS AS IDENTITY,
    account_id NUMBER NOT NULL,
    status VARCHAR2(15) NOT NULL,
    amount NUMBER NOT NULL,
    created_time date default sysdate NOT NULL,
    PRIMARY KEY(order_id)
);