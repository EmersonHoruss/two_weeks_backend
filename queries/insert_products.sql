USE two_weeks_backend;

DESCRIBE product;

INSERT INTO product (
    `type`, `brand`, `size`, `stock`, `purchase_price`, `sell_price_normal`, 
    `sell_price_wholesale1`, `sell_price_wholesale2`, `name`, `code`, `activated`)
VALUES
('Electronics', 'Samsung', 'Medium', 50, 100.00, 150.00, 140.00, 130.00, 'Samsung TV', 'PRD001', true),
('Electronics', 'Sony', 'Large', 30, 200.00, 300.00, 280.00, 270.00, 'Sony TV', 'PRD002', true),
('Appliances', 'LG', 'Small', 20, 80.00, 120.00, 110.00, 100.00, 'LG Fridge', 'PRD003', true),
('Furniture', 'Ikea', 'Large', 10, 150.00, 220.00, 200.00, 190.00, 'Ikea Table', 'PRD004', true),
('Electronics', 'Apple', 'Small', 15, 500.00, 600.00, 580.00, 570.00, 'Apple iPhone', 'PRD005', true),
('Electronics', 'Dell', 'Medium', 40, 300.00, 400.00, 380.00, 370.00, 'Dell Laptop', 'PRD006', true),
('Electronics', 'HP', 'Medium', 25, 250.00, 350.00, 330.00, 320.00, 'HP Laptop', 'PRD007', true),
('Appliances', 'Whirlpool', 'Large', 12, 400.00, 550.00, 530.00, 510.00, 'Whirlpool Washing Machine', 'PRD008', true),
('Furniture', 'Ashley', 'Medium', 20, 90.00, 150.00, 140.00, 130.00, 'Ashley Sofa', 'PRD009', true),
('Electronics', 'Lenovo', 'Small', 18, 220.00, 300.00, 290.00, 280.00, 'Lenovo Tablet', 'PRD010', true),
('Electronics', 'Bose', 'Small', 35, 120.00, 200.00, 190.00, 180.00, 'Bose Speaker', 'PRD011', true),
('Appliances', 'Panasonic', 'Medium', 22, 100.00, 150.00, 140.00, 130.00, 'Panasonic Microwave', 'PRD012', true),
('Electronics', 'Microsoft', 'Small', 15, 300.00, 450.00, 430.00, 420.00, 'Microsoft Surface', 'PRD013', true),
('Furniture', 'Herman Miller', 'Medium', 8, 400.00, 600.00, 580.00, 570.00, 'Herman Miller Chair', 'PRD014', true),
('Appliances', 'Bosch', 'Large', 10, 350.00, 500.00, 480.00, 470.00, 'Bosch Dishwasher', 'PRD015', true),
('Electronics', 'Philips', 'Medium', 25, 50.00, 100.00, 90.00, 85.00, 'Philips Headphones', 'PRD016', true),
('Appliances', 'Dyson', 'Medium', 18, 150.00, 250.00, 240.00, 230.00, 'Dyson Vacuum', 'PRD017', true),
('Furniture', 'West Elm', 'Large', 5, 500.00, 700.00, 680.00, 670.00, 'West Elm Bed', 'PRD018', true),
('Electronics', 'Canon', 'Small', 20, 200.00, 350.00, 340.00, 330.00, 'Canon Camera', 'PRD019', true),
('Appliances', 'GE', 'Large', 8, 400.00, 600.00, 580.00, 570.00, 'GE Refrigerator', 'PRD020', true),
('Electronics', 'JBL', 'Small', 35, 60.00, 120.00, 110.00, 100.00, 'JBL Speaker', 'PRD021', true),
('Furniture', 'La-Z-Boy', 'Medium', 15, 300.00, 450.00, 430.00, 420.00, 'La-Z-Boy Recliner', 'PRD022', true),
('Appliances', 'KitchenAid', 'Small', 30, 120.00, 220.00, 210.00, 200.00, 'KitchenAid Mixer', 'PRD023', true),
('Electronics', 'GoPro', 'Small', 25, 200.00, 350.00, 340.00, 330.00, 'GoPro Camera', 'PRD024', true),
('Appliances', 'Samsung', 'Large', 10, 500.00, 700.00, 680.00, 670.00, 'Samsung Dryer', 'PRD025', true),
('Appliances1', 'Samsung1', 'Large1', 101, 5001.00, 7001.00, 6801.00, 6701.00, 'Samsung Dryer1', 'PRD0251', true);

SELECT * FROM product\G;

SELECT 'Insertion of products done successfully!' AS message;
