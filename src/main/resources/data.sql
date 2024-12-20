-- Insert admin user (password is 'admin123')
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES ('admin', 'admin@groceryapp.com', '$2a$10$N/qM0TqkBaHVhKUK6hCLxuDj/KO4IdI0WgHi9Q6sNm8UENAzCTzhy', 'Admin', 'User', 'ADMIN');

-- Insert sample products
INSERT INTO products (name, description, price, stock_quantity, category, image_url) VALUES
-- Fruits
('Apple', 'Fresh red apples', 0.50, 100, 'Fruits', 'https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6'),
('Banana', 'Ripe yellow bananas', 0.30, 150, 'Fruits', 'https://images.unsplash.com/photo-1543218024-57a70143c369'),
('Orange', 'Sweet juicy oranges', 0.60, 80, 'Fruits', 'https://images.unsplash.com/photo-1547514701-42782101795e'),

-- Vegetables
('Carrot', 'Fresh organic carrots', 0.40, 120, 'Vegetables', 'https://images.unsplash.com/photo-1598170845058-32b9d6a5da37'),
('Tomato', 'Ripe red tomatoes', 0.45, 90, 'Vegetables', 'https://images.unsplash.com/photo-1546470427-e26e6bf803b7'),
('Lettuce', 'Crisp green lettuce', 1.20, 60, 'Vegetables', 'https://images.unsplash.com/photo-1622206151226-18ca2c9ab4a1'),

-- Dairy
('Milk', 'Fresh whole milk', 3.99, 50, 'Dairy', 'https://images.unsplash.com/photo-1563636619-e9143da7973b'),
('Cheese', 'Cheddar cheese block', 4.99, 40, 'Dairy', 'https://images.unsplash.com/photo-1618164436241-4473940d1f5c'),
('Yogurt', 'Plain Greek yogurt', 2.99, 45, 'Dairy', 'https://images.unsplash.com/photo-1563227812-0ea4c22e6cc8'),

-- Beverages
('Orange Juice', 'Fresh squeezed orange juice', 4.99, 30, 'Beverages', 'https://images.unsplash.com/photo-1621506289937-a8e4df240d0b'),
('Green Tea', 'Organic green tea bags', 3.99, 45, 'Beverages', 'https://images.unsplash.com/photo-1627435601361-ec25f5b1d0e5');
