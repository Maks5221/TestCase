--liquibase formatted sql

--changeset Maksim:1
INSERT INTO goods (name, description, price, availability)
VALUES ('Laptop', 'Gaming laptop', 64699, 'AVAILABLE'),
        ('Tablet', 'Diagonal 35', 15299.45, 'ABSENT'),
        ('Mobile phone', 'The latest model', 32544, 'ABSENT'),
        ('PC', '', 85999.99, 'AVAILABLE'),
        ('TV', 'Samsung', 212699, 'AVAILABLE'),
        ('Microwave', '', 5000, 'AVAILABLE');