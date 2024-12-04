USE two_weeks_backend;

DESCRIBE brand;
DESCRIBE size;
DESCRIBE type;
DESCRIBE product;

-- Inserting Brands
INSERT INTO brand (id, name, activated) VALUES
(1, 'Brand A', true),
(2, 'Brand B', true),
(3, 'Brand C', true),
(4, 'Brand D', true),
(5, 'Brand E', true);

-- Inserting Sizes
INSERT INTO size (id, name, activated) VALUES
(1, 'Small', true),
(2, 'Medium', true),
(3, 'Large', true),
(4, 'Extra Large', true),
(5, 'XXL', true);

-- Inserting Types
INSERT INTO type (id, name, activated) VALUES
(1, 'Type A', true),
(2, 'Type B', true),
(3, 'Type C', true),
(4, 'Type D', true),
(5, 'Type E', true);

-- Inserting Products
INSERT INTO product (id, stock, purchase_price, sell_price_normal, sell_price_wholesale1, sell_price_wholesale2, name, code, type_id, brand_id, size_id, activated) VALUES
(1, 10, 100.50, 150.75, 140.00, 130.00, '1-1-1', '1-1-1', 1, 1, 1, true),
(2, 20, 200.00, 250.00, 240.00, 230.00, '1-2-2', '1-2-2', 1, 2, 2, true),
(3, 15, 150.00, 200.00, 190.00, 180.00, '1-3-3', '1-3-3', 1, 3, 3, true),
(4, 30, 300.00, 350.00, 340.00, 330.00, '1-4-4', '1-4-4', 1, 4, 4, true),
(5, 25, 250.00, 300.00, 290.00, 280.00, '1-5-5', '1-5-5', 1, 5, 5, true),
(6, 10, 110.00, 160.00, 150.00, 140.00, '2-1-2', '2-1-2', 2, 1, 2, true),
(7, 20, 210.00, 260.00, 250.00, 240.00, '2-2-3', '2-2-3', 2, 2, 3, true),
(8, 15, 160.00, 210.00, 200.00, 190.00, '2-3-4', '2-3-4', 2, 3, 4, true),
(9, 30, 310.00, 360.00, 350.00, 340.00, '2-4-5', '2-4-5', 2, 4, 5, true),
(10, 25, 260.00, 310.00, 300.00, 290.00, '2-5-1', '2-5-1', 2, 5, 1, true),
(11, 12, 120.00, 170.00, 160.00, 150.00, '3-1-2', '3-1-2', 3, 1, 2, true),
(12, 22, 220.00, 270.00, 260.00, 250.00, '3-2-3', '3-2-3', 3, 2, 3, true),
(13, 18, 170.00, 220.00, 210.00, 200.00, '3-3-4', '3-3-4', 3, 3, 4, true),
(14, 35, 320.00, 370.00, 360.00, 350.00, '3-4-5', '3-4-5', 3, 4, 5, true),
(15, 28, 270.00, 320.00, 310.00, 300.00, '3-5-1', '3-5-1', 3, 5, 1, true),
(16, 14, 130.00, 180.00, 170.00, 160.00, '4-1-2', '4-1-2', 4, 1, 2, true),
(17, 24, 230.00, 280.00, 270.00, 260.00, '4-2-3', '4-2-3', 4, 2, 3, true),
(18, 19, 180.00, 230.00, 220.00, 210.00, '4-3-4', '4-3-4', 4, 3, 4, true),
(19, 32, 330.00, 380.00, 370.00, 360.00, '4-4-5', '4-4-5', 4, 4, 5, true),
(20, 26, 280.00, 330.00, 320.00, 310.00, '4-5-1', '4-5-1', 4, 5, 1, true),
(21, 16, 140.00, 190.00, 180.00, 170.00, '5-1-2', '5-1-2', 5, 1, 2, true),
(22, 26, 240.00, 290.00, 280.00, 270.00, '5-2-3', '5-2-3', 5, 2, 3, true),
(23, 21, 190.00, 240.00, 230.00, 220.00, '5-3-4', '5-3-4', 5, 3, 4, true),
(24, 34, 340.00, 390.00, 380.00, 370.00, '5-4-5', '5-4-5', 5, 4, 5, true),
(25, 29, 290.00, 340.00, 330.00, 320.00, '5-5-1', '5-5-1', 5, 5, 1, true),
(26, 18, 150.00, 200.00, 190.00, 180.00, '1-1-2', '1-1-2', 1, 1, 2, true);

SELECT * FROM brand\G;
SELECT * FROM size\G;
SELECT * FROM type\G;
SELECT * FROM product\G;

SELECT 'Insertion of products done successfully!' AS message;
