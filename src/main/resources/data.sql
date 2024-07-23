insert into roles(role_name)
values ('ROLE_ADMIN'),
       ('ROLE_STAFF');

insert into users(username, password, first_name, last_name, email, phone_number, salary, notes)
values ('Patron', '$2a$12$bdJZ0EFvKN90z9Ym1fG0TO3/47xqWcDX0wYiUznc/4svsW7QWrEu.', 'Al', 'Patron', 'a.patron@kantjil.nl', 31612345678, 4000, ''),
       ('janjanssen', '$2a$12$bdJZ0EFvKN90z9Ym1fG0TO3/47xqWcDX0wYiUznc/4svsW7QWrEu.', 'Jan', 'Jansen', 'j.janssen@kantjil.nl', 31687654321, 3000, ''),
       ('supermiep', '$2a$12$bdJZ0EFvKN90z9Ym1fG0TO3/47xqWcDX0wYiUznc/4svsW7QWrEu.', 'Miep', 'LaStrade', 'supermiep@mieperdemiep.miep', 31656783421, 2500, '');

insert into user_roles(users_username, roles_role_name)
values ('Patron', 'ROLE_ADMIN'),
       ('Patron', 'ROLE_STAFF'),
       ('janjanssen', 'ROLE_STAFF'),
       ('supermiep', 'ROLE_STAFF');

insert into products(name, state, amount, amount_unit, buy_price, sell_price, is_for_retail, stock, notes)
values ('jasmine rice', 1, 10, 'kilo', 28.5, 50, false, 5, ''),
       ('corned beef', 1, 340, 'gram', 2.89, 3.95, true, 10, ''),
       ('leak', 0, 1, 'piece', 0.5, 0.89, true, 20, ''),
       ('onion', 0, 10, 'kilo', 8, 15, false, 40, ''),
       ('red bell pepper', 0, 50, 'pieces', 3.45, 8.2, false, 12, ''),
       ('egg', 0, 100, 'pieces', 15.78, 35.99, false, 2, 'Store in the back of walk-in refrigerator'),
       ('fried onion bits', 1, 1, 'kilo', 3.29, 7.99, true, 30, ''),
       ('bamboo skewer sticks', 1, 100, 'pieces', 0.1, 0.5, true, 120, 'soak in water day before use to prevent charring'),
       ('pork belly', 0, 5, 'kilo', 54.60, 75.8, false, 2, ''),
       ('ham steak', 0, 5, 'kilo', 22.38, 48.87, false, 2, ''),
       ('ketjap manis', 1, 5, 'liter', 12.88, 27.74, false, 6, ''),
       ('tomato ketchup', 1, 3, 'liter', 8.99, 14.80, false, 4, ''),
       ('spring roll wrappers', 2, 50, 'pieces', 3.26, 3.75, true, 35, ''),
       ('bamboo shoots', 1, 500, 'gram', 2.00, 2.69, true, 60, ''),
       ('tahoe', 3, 500, 'gram', 1.87, 2.59, true, 16, ''),
       ('small shrimp', 0, 1, 'kilo', 15.36, 28.99, false, 8, ''),
       ('kroepoek', 1, 100, 'gram', 0.65, 1.29, true, 24, '');

insert into dishes(name, servings, production_price, sell_price, is_appraised, stock, notes)
values ('nasi goreng', 5, 0, 0, false, 1, ''),
       ('sate babi', 8, 0, 0, false,  1, ''),
       ('spring rolls', 6, 0, 0, false, 1, '');

insert into dish_products(dishes_id, products_id)
values (1, 1),
       (1, 2),
       (1, 3);

insert into caterings(date_and_time, number_of_people, total_cost_price, total_sell_price, agreed_price, is_appraised, notes)
values ('2024-10-28T15:00:00', 20, 1650, 0, 5000, false, ''),
       ('2024-09-20T12:00:00', 12, 1000, 0, 3000, false, '');

insert into addresses(name, street, house_number, house_number_suffix, postal_code, city, notes)
values ('Bert Steentjes residence', 'Nachtegaalstraat', 34, '', '1234AB', 'Hogermolenveen', ''),
       ('Partycentrum Den Blije Eend', 'Champagneweg', 155, 'a', '1234AB', 'Eenderdam', 'Only (un)loading allowed at parking near kitchen-entrance'),
       ('Clara Vache residence', 'Kopermolenweg', 40, 'III', '1234AB', 'Lager Eenderdorp', '');

insert into customers(first_name, last_name, email, phone_number, notes)
values ('Bert', 'Steentjes', 'bertbeton@betonnenbert.nl', 31690483725, ''),
       ('Clara', 'Vache', 'c.vache@koehandel.nl', 3160000003, ''),
       ('Koos', 'Druk', 'info@koosdruk.nl', 3164839455, ''),
       ('Mark', 'Webber', 'neeeenanderemark@formule1.vroem', 3167888854, 'Do NOT send Miep on any caterings he orders.');

insert into orders(title, status, is_catering)
values ('Bert Steentjes Birthday party', 0, true);

