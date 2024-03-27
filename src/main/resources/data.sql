INSERT INTO users (first_name, last_name, email, password)
VALUES
    ('admin', 'admin', 'admin@tuz.com', '$2a$10$NtoHOZgY.h1UO9gw4NnnAOy9nLvAGMpHMYnAojnNYGOXV8F5bDuEe'),
    ('John', 'Doe', 'john.doe@example.com', 'password123'),
    ('Alice', 'Smith', 'alice.smith@example.com', 'secret456'),
    ('Bob', 'Johnson', 'bob.johnson@example.com', 'foobar789');

INSERT INTO barbershop (name, address, lat, lng, image_url)
VALUES
    ('Kyiv Barbershop', '1 Khreshchatyk St, Kyiv, Ukraine', 50.4501, 30.5234, 'https://lh5.googleusercontent.com/p/AF1QipMMNrW8yhuTjemqpYlhr-qJt1dkcIuBCQ2-h83F=w426-h240-k-no'),
    ('Razors Edge', '15 Tarasa Shevchenko Blvd, Kyiv, Ukraine', 50.4488, 30.5005, 'https://media-cdn.tripadvisor.com/media/photo-s/1c/41/d8/26/barbershop.jpg'),
    ('Old Town Barbers', '35 Volodymyrska St, Kyiv, Ukraine', 50.4500, 30.5219, 'https://f.kyivmaps.com/location/3554/6zLlZ.jpg'),
    ('The Barber Lounge', '22 Velyka Vasylkivska St, Kyiv, Ukraine', 50.4382, 30.5174, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRExc--qUqq8C8faJRjamI6m0JE9FdFZayiDzactQwq3Q&s'),
    ('Cut & Style', '9 Antonovycha St, Kyiv, Ukraine', 50.4326, 30.5181, 'https://i.pinimg.com/736x/c1/96/b3/c196b32e1a017cf611bc190492cfa63d.jpg'),
    ('Gentlemans Cut', '17/2 Velyka Zhytomyrska St, Kyiv, Ukraine', 50.4542, 30.5254, 'https://static.tildacdn.com/tild3064-3933-4030-b538-393836373566/DSC02601.jpg');

INSERT INTO barber (first_name, last_name, barbershop_id, image_url)
VALUES
    ('Michael', 'Smith', 1, 'https://titlecitybarbers.com/assets/static/dany.7474da0.314044490d04151a74fee4f20b56d7ab.jpg'),
    ('David', 'Jones', 2, 'https://losbarberosclassicbarbershop.com/wp-content/uploads/2019/06/Profile-_0006_Edwin.jpg'),
    ('Christopher', 'Wilson', 3, 'https://titlecitybarbers.com/assets/static/joey.7474da0.b51048af33aad033c1d14482720d4410.jpg'),
    ('Jessica', 'Brown', 4, 'https://img.freepik.com/free-photo/confident-looking-young-beautiful-female-barber-uniform-holding-scissors-with-comb-isolated-pink-wall_141793-105656.jpg'),
    ('Jennifer', 'Davis', 5, 'https://img.freepik.com/free-photo/confident-young-slavic-female-barber-wearing-uniform-standing-profile-view-holding-spray-bottle-straight-razor-comb_141793-98515.jpg'),
    ('Matthew', 'Miller', 6, 'https://360degreebarbershop.com/wp-content/uploads/2023/06/lucas-barber-pose.jpg');

INSERT INTO visit (barber_id, user_id, datetime, duration_min)
VALUES
    (1, 2, '2024-02-29 10:00:00', 60),
    (2, 3, '2024-02-29 11:30:00', 45),
    (3, 4, '2024-02-29 13:00:00', 30),
    (4, 2, '2024-02-29 14:00:00', 60),
    (5, 3, '2024-02-29 15:30:00', 45),
    (6, 4, '2024-02-29 17:00:00', 30);