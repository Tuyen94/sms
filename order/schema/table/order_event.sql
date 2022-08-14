CREATE TABLE order_event(
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    publish_status VARCHAR2(10),
    order_id NUMBER NOT NULL,
    account_id NUMBER NOT NULL,
    status VARCHAR2(15) NOT NULL,
    amount NUMBER NOT NULL,
    created_time date NOT NULL,
    PRIMARY KEY(id)
);