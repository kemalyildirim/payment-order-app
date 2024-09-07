CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS customers
(
    id           UUID                NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name         VARCHAR(100)        NOT NULL,
    email        VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    created_at   TIMESTAMP                                DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS products
(
    id             UUID           NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name           VARCHAR(255)   NOT NULL,
    description    TEXT,
    price          NUMERIC(10, 2) NOT NULL,
    stock_quantity INT                                 DEFAULT 0,
    created_at     TIMESTAMP                           DEFAULT NOW()
);

CREATE TYPE ORDER_STATUS AS ENUM ('pending', 'shipped', 'completed');

CREATE TABLE IF NOT EXISTS orders
(
    id          UUID           NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    customer_id INT REFERENCES customers (id),
    status      ORDER_STATUS   NOT NULL             DEFAULT 'pending',
    total_price NUMERIC(10, 2) NOT NULL,
    created_at  TIMESTAMP                           DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS order_items
(
    id         UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    order_id   UUID REFERENCES orders (id) ON DELETE CASCADE,
    product_id UUID REFERENCES products (id),
    quantity   INT  NOT NULL
);