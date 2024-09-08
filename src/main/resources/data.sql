insert into roles(role_name)
values ('ROLE_ADMIN'),
       ('ROLE_STAFF');

insert into users(username, password, first_name, last_name, email, phone_number, salary, notes)
values ('Ben', '$2a$12$bdJZ0EFvKN90z9Ym1fG0TO3/47xqWcDX0wYiUznc/4svsW7QWrEu.', 'Ben', 'de Baas', 'b.debaas@kantjil.nl', 31612345678, 4000, ''),
       ('Jan', '$2a$12$bdJZ0EFvKN90z9Ym1fG0TO3/47xqWcDX0wYiUznc/4svsW7QWrEu.', 'Jan', 'Jansen', 'j.janssen@kantjil.nl', 31687654321, 3000, '');

insert into user_roles(users_username, roles_role_name)
values ('Ben', 'ROLE_ADMIN'),
       ('Ben', 'ROLE_STAFF'),
       ('Jan', 'ROLE_STAFF');

insert into products(name, state, amount, amount_unit, buy_price, sell_price, is_for_retail, stock, notes)
values ('jasmine rice', 1, 300, 'gram', 0.85, 1.5, false, 5, ''),
       ('corned beef', 1, 340, 'gram', 2.89, 3.95, true, 10, ''),
       ('leak', 0, 1, 'piece', 0.5, 0.89, true, 20, ''),
       ('onion', 0, 1, 'piece', 0.2, 0.5, true, 40, ''),
       ('red bell pepper', 0, 1, 'piece', 0.5, 0.9, true, 12, ''),
       ('egg', 0, 1, 'pieces', 0.15, 0.35, false, 2, 'store in the back of walk-in refrigerator'),
       ('fried onion bits', 1, 200, 'gram', 0.75, 1.65, true, 30, ''),
       ('bamboo skewer sticks', 1, 100, 'pieces', 0.1, 0.5, true, 120, 'soak in water day before use to prevent charring'),
       ('pork belly', 0, 500, 'gram', 5.46, 7.58, false, 2, ''),
       ('ham steak', 0, 500, 'gram', 2.23, 4.88, false, 2, ''),
       ('ketjap manis', 1, 200, 'milliliter', 0.51, 1.12, false, 6, ''),
       ('tomato ketchup', 1, 200, 'milliliter', 0.6, 0.99, false, 4, ''),
       ('spring roll wrappers', 2, 24, 'pieces', 1.63, 1.87, true, 35, ''),
       ('bamboo shoots', 1, 500, 'gram', 2.00, 2.69, true, 60, ''),
       ('tahoe', 3, 500, 'gram', 1.87, 2.59, true, 16, ''),
       ('small shrimp', 0, 200, 'gram', 3.16, 5.95, false, 8, ''),
       ('carrot', 0, 1, 'piece', 0.1, 0.15, false, 8, ''),
       ('kroepoek', 1, 100, 'gram', 0.65, 1.29, true, 24, ''),
       ('kroepoek', 1, 1500, 'gram', 9.75, 19.35, false, 5, ''),
       ('sambal oelek', 1, 300, 'gram', 0.9, 2.4, true, 15, '');

insert into dishes(name, servings, production_price, sell_price, is_appraised, stock, notes)
values ('nasi goreng', 5, 10.09, 15.59, true, 5, ''),
       ('sate babi', 8, 14.65, 24.22, true,  5, ''),
       ('spring rolls', 6, 13.76, 20.75, true, 5, ''),
       ('fried pork belly', 4, 10.46, 15.08, true, 5, '');

insert into dish_products(dishes_id, products_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 10),
       (2, 11),
       (2, 12),
       (3, 13),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),
       (4, 9);

insert into addresses(name, street, house_number, house_number_suffix, postal_code, city, notes)
values ('Toko Kantjil', 'Winkelweg', 1, '', '1234AB', 'Tokodorp', ''),
       ('Bert Steentjes residence', 'Nachtegaalstraat', 34, '', '1234AB', 'Hogermolenveen', ''),
       ('Partycentrum Den Blije Eend', 'Champagneweg', 155, 'a', '1234AB', 'Eenderdam', 'Only (un)loading allowed at parking near kitchen-entrance'),
       ('Clara Vache residence', 'Kopermolenweg', 40, 'III', '1234AB', 'Lager Eenderdorp', '');

insert into customers(first_name, last_name, email, phone_number, notes, address_id)
values ('Kassa', '01', 'info@kantjil.nl', 3100000000, 'Main point of sales in physical store.', 1),
       ('Bert', 'Steentjes', 'bertbeton@betonnenbert.nl', 31690483725, '', 2),
       ('Clara', 'Vache', 'c.vache@koehandel.nl', 3160000003, '', 4);

insert into caterings(date_and_time, number_of_people, total_cost_price, total_sell_price, agreed_price, is_appraised, notes, address_id)
values ('2024-10-28T15:00:00', 15, 1012.8, 1531.69, 2000, true, '', 3),
       ('2024-10-20T17:00:00', 6, 249.24, 376.56, 500, true, '', 2),
       ('2024-10-20T17:00:00', 6, 249.24, 376.56, 500, true, '', 2);

insert into catering_dishes(caterings_id, dishes_id)
values (1, 1),
       (1, 1),
       (1, 1),
       (1, 2),
       (1, 2),
       (1, 3),
       (1, 3),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2);

insert into catering_products(caterings_id, products_id)
values (1, 7),
       (1, 7),
       (1, 7),
       (1, 19);

insert into orders(title, created_at, status, total_cost, total_price, is_catering_order, catering_id, customer_id, is_appraised, created_by)
values ('Bert Steentjes Birthday party', '2024-07-27T13:56:47.584277', 0, 1012.8, 1531.69, true, 1, 2, true, 'Ben'),
       ('Order 2', '2024-07-27T13:56:47.584277', 2, 25.25, 39.28, false, null, 1, true, 'Jan'),
       ('Test catering order', '2024-01-27T13:56:47.584277', 0, 12.34, 56.78, true, null, null, false, 'Ben'),
       ('Test order no catering', '2024-01-27T13:56:47.584277', 0, 0, 0, false, null, null, false, 'Ben'),
       ('Test catering order 2', '2024-01-27T13:56:47.584277', 0, 12.34, 56.78, true, 2, 2, true, 'Ben');

insert into order_dishes(orders_id, dishes_id)
values (2, 1),
       (2, 3),
       (4, 1);

insert into order_products(orders_id, products_id)
values (2, 7),
       (2, 18),
       (4, 18);

insert into invoices(final_price, is_paid, order_id, notes)
values (2000, false, 1, ''),
       (39.28, true, 2, ''),
       (1234.56, false, null, '');

