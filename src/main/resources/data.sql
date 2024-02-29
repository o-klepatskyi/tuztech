INSERT INTO users (first_name, last_name, email, password)
VALUES
    ('admin', 'admin', 'admin@tuz.com', 'admin'),
    ('John', 'Doe', 'john.doe@example.com', 'password123'),
    ('Alice', 'Smith', 'alice.smith@example.com', 'secret456'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', 'foobar789');

INSERT INTO barbershop (name, address, lat, lng)
VALUES
    ('Kyiv Barbershop', '1 Khreshchatyk St, Kyiv, Ukraine', 50.4501, 30.5234),
    ('Razors Edge', '15 Tarasa Shevchenko Blvd, Kyiv, Ukraine', 50.4488, 30.5005),
    ('Old Town Barbers', '35 Volodymyrska St, Kyiv, Ukraine', 50.4500, 30.5219),
    ('The Barber Lounge', '22 Velyka Vasylkivska St, Kyiv, Ukraine', 50.4382, 30.5174),
    ('Cut & Style', '9 Antonovycha St, Kyiv, Ukraine', 50.4326, 30.5181),
    ('Gentlemans Cut', '17/2 Velyka Zhytomyrska St, Kyiv, Ukraine', 50.4542, 30.5254);

INSERT INTO barber (first_name, last_name, barbershop_id)
VALUES
    ('Michael', 'Smith', 1),
    ('David', 'Jones', 2),
    ('Christopher', 'Wilson', 3),
    ('Jessica', 'Brown', 4),
    ('Jennifer', 'Davis', 5),
    ('Matthew', 'Miller', 6);

INSERT INTO visit (barber_id, user_id, datetime, duration_min)
VALUES
    (1, 2, '2024-02-29 10:00:00', 60),
    (2, 3, '2024-02-29 11:30:00', 45),
    (3, 4, '2024-02-29 13:00:00', 30),
    (4, 2, '2024-02-29 14:00:00', 60),
    (5, 3, '2024-02-29 15:30:00', 45),
    (6, 4, '2024-02-29 17:00:00', 30);