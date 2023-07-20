
--перед первым запуском приложения
create database calendar;


--после первого запуска приложения
INSERT INTO calendar.users (email, password, username)
VALUES
    ('admin@mail.ru', '$2y$10$QYPvURE5SVkwY2QmyNuevuVicftfhzyNoBhcYhv/6yAFIB5TtcnMO','admin'),
    ('user@mail.ru', '$2y$10$ty0tygx9.2Yxlmp/trpYp.a0C91jF2eELsxVBYgtTb3DH0cfaP6le','user');

INSERT INTO calendar.roles (role)
VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');

INSERT INTO calendar.users_roles (user_id, role_id)
VALUES
    (1,1),
    (2,2);