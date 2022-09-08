CREATE TABLE order_outbox(
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    order_id NUMBER NOT NULL,
    operation VARCHAR2(10),
    status VARCHAR2(10),
    PRIMARY KEY(id)
);
--
CREATE TABLE orderdb.order_outbox(
    id BIGSERIAL PRIMARY KEY,
    order_id bigint NOT NULL,
    operation VARCHAR(10),
    status VARCHAR(10)
);