-- ========================================
-- ROLES
-- ========================================
INSERT INTO role (name, permission_description)
VALUES
('ADMIN', 'Admin full access'),
('USER', 'Normal user access'),
('MANAGER', 'Manager user access')
ON CONFLICT (name) DO NOTHING;

-- ========================================
-- PAYMENT METHODS
-- ========================================
INSERT INTO payment_method (name, provider)
VALUES
('card', 'Personal'),
('cash', 'Bank')
ON CONFLICT (name) DO NOTHING;

-- ========================================
-- COSTS
-- ========================================
INSERT INTO cost (name, value_per_hour)
VALUES
('Ordinary', 2.5),
('Promo2X1', 1.3),
('SpecialDay', 1.9)
ON CONFLICT (name) DO NOTHING;

-- ========================================
-- TYPE EXPERIENCE
-- ========================================
-- Usamos subconsulta para obtener cost_id
INSERT INTO type_experience (name, cost_id)
VALUES
('RENTAL', (SELECT id FROM cost WHERE name='Ordinary')),
('TOUR', (SELECT id FROM cost WHERE name='Ordinary'))
ON CONFLICT (name) DO NOTHING;

-- ========================================
-- BIKES
-- ========================================
INSERT INTO bike (serial_number, brand, model, year, color, status, type)
VALUES
('MTB-12345', 'Trek', 'X-Caliber 8', '2023', 'Negro', 'AVAILABLE', 'MOUNTAIN'),
('ROAD-54321', 'Specialized', 'Tarmac SL7', '2022', 'Rojo', 'RENTED', 'ROAD'),
('EBIKE-77777', 'Giant', 'Explore E+', '2021', 'Azul', 'MAINTENANCE', 'ELECTRIC')
ON CONFLICT (serial_number) DO NOTHING;

-- ========================================
-- USERS (con hash Bcrypt)
-- ========================================
INSERT INTO users (email, password, role_id)
VALUES
('gllatser@gobike.com', '$2a$10$wH6H9ZUp.mZ3sGcVdP5Gyu/oz5bXfPmF6OlvuW3FPHRF0Pj7oLkxC',
    (SELECT id FROM role WHERE name='ADMIN')),
('aliciaquiroga@gmail.com', '$2a$10$L4wRZXYq76Y4ZJ3Pl5AbEuYiwDdBkMk/8w1ZkW2JzZQcO9Tqv2ZQy',
    (SELECT id FROM role WHERE name='USER'))
ON CONFLICT (email) DO NOTHING;

-- ========================================
-- PERSONAL DATA
-- ========================================
INSERT INTO personal_data (user_id, first_name, last_name, phone_primary, phone_secondary, address, payment_method_id)
VALUES
((SELECT id FROM users WHERE email='gllatser@gobike.com'), 'Gonzalo Manuel', 'Llatser', '654207965', '915008632', 'Calle San Juan 23, Valencia',
    (SELECT id FROM payment_method WHERE name='card')),
((SELECT id FROM users WHERE email='aliciaquiroga@gmail.com'), 'Alicia', 'Quiroga', '698423665', '641298555', 'Av. Cristobal de la Plana 2, Madrid',
    (SELECT id FROM payment_method WHERE name='cash'))
ON CONFLICT DO NOTHING;

-- ========================================
-- EXPERIENCES
-- ========================================
INSERT INTO experience (type_id, bike_id)
VALUES
((SELECT id FROM type_experience WHERE name='RENTAL'), (SELECT id FROM bike WHERE serial_number='ROAD-54321')),
((SELECT id FROM type_experience WHERE name='TOUR'), (SELECT id FROM bike WHERE serial_number='EBIKE-77777'))
ON CONFLICT DO NOTHING;

-- ========================================
-- PAYMENTS
-- ========================================
INSERT INTO payment (transaction_id, date, status)
VALUES
('TXN-12345', now(), 'PAID'),
('TXN-67890', now(), 'PENDING')
ON CONFLICT (transaction_id) DO NOTHING;

-- ========================================
-- RESERVATIONS
-- ========================================
INSERT INTO reservation (reservation_date, user_id, payment_id, experience_id, status)
VALUES
(now(),
 (SELECT id FROM users WHERE email='gllatser@gobike.com'),
 (SELECT id FROM payment WHERE transaction_id='TXN-12345'),
 (SELECT id FROM experience WHERE bike_id=(SELECT id FROM bike WHERE serial_number='ROAD-54321')),
 'CONFIRMED'
),
(now(),
 (SELECT id FROM users WHERE email='aliciaquiroga@gmail.com'),
 (SELECT id FROM payment WHERE transaction_id='TXN-67890'),
 (SELECT id FROM experience WHERE bike_id=(SELECT id FROM bike WHERE serial_number='EBIKE-77777')),
 'PENDING'
)
ON CONFLICT DO NOTHING;
