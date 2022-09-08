CREATE TABLE orders(
    order_id NUMBER GENERATED ALWAYS AS IDENTITY,
    account_id NUMBER NOT NULL,
    status VARCHAR2(15) NOT NULL,
    amount NUMBER NOT NULL,
    created_time date default sysdate NOT NULL,
    PRIMARY KEY(order_id)
);
--
CREATE TABLE orderdb.orders(
    order_id BIGSERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    status VARCHAR(15) NOT NULL,
    amount INTEGER NOT NULL,
    created_time timestamp NOT NULL DEFAULT NOW()
);