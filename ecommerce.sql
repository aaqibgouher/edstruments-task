-- USERS TABLE
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) DEFAULT 'customer', -- 'customer' or 'admin'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PRODUCTS TABLE
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ORDERS TABLE
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    status VARCHAR(20) DEFAULT 'pending', -- e.g., pending, shipped, delivered
    total_amount DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ORDER ITEMS TABLE
CREATE TABLE order_items (
    id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(id) ON DELETE CASCADE,
    product_id INT REFERENCES products(id),
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- USERS
INSERT INTO users (name, email, password, role) VALUES
('Alice', 'alice@example.com', 'password123', 'customer'),
('Bob', 'bob@example.com', 'admin123', 'admin');

-- PRODUCTS
INSERT INTO products (name, description, price, stock_quantity) VALUES
('Laptop', 'Powerful gaming laptop', 1200.00, 10),
('Phone', 'Latest smartphone', 800.00, 20),
('Headphones', 'Noise-cancelling headphones', 150.00, 50);

-- ORDERS
INSERT INTO orders (user_id, status, total_amount) VALUES
(1, 'pending', 1950.00);

-- ORDER ITEMS
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 1200.00),
(1, 2, 1, 800.00),
(1, 3, 1, 150.00);
