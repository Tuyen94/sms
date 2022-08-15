CREATE TABLE order_outbox(
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    order_id NUMBER NOT NULL,
    operation VARCHAR2(10),
    status VARCHAR2(10),
    PRIMARY KEY(id)
);