insert into person (id, name, gender, day_of_born, email, height, weight, purpose, activity)
values ('1dfb0519-e0be-4f40-8a73-8bd62237127b',
        'Alex Gustov',
        0,
        '1991-12-07',
        'a.gustov@gmail.com',
        170,
        80,
        0,
        3),
       ('1fae4382-75a7-4eb5-a244-e1251e01cdf6',
        'Anna Felis',
        1,
        '1989-11-02',
        'a.felis@yandex.ru',
        160,
        70,
        1,
        3),
       ('b0ea4671-9e35-4443-9dcf-1bb9ad6846d1',
        'Igor Karkarov',
        0,
        '1945-01-11',
        'i.karkarov@rambler.ru',
        185,
        100,
        2,
        3);

insert into dish(id, title, caloric_content, proteins, fats, carbs)
VALUES ('fb262d1a-5d77-4d45-a271-ad78375e6cef',
        'Banana',
        89,
        1,
        1,
        23),
       ('325030ed-8163-4e6f-87a9-a6ca8ac1df92',
        'Apple',
        52,
        1,
        1,
        14),
       ('ebe4d182-da23-4238-96b5-6324132fec20',
        'Meat dumplings (100g)',
        275000,
        12,
        12,
        29);

insert into eating (date_time, id, person_id)
values ('2025-03-25 16:18:58.000000', '3b04c377-7ff1-487f-989f-4cea69e54f11', '1dfb0519-e0be-4f40-8a73-8bd62237127b'),
       ('2025-03-25 15:18:58.000000', '2fb16d24-6ba9-4a6a-99af-fa1b3aeb13ae', '1fae4382-75a7-4eb5-a244-e1251e01cdf6'),
       ('2025-03-25 14:18:58.000000', 'b4232eb2-32ee-4e22-97e0-5656ff41b206', 'b0ea4671-9e35-4443-9dcf-1bb9ad6846d1');

insert into dish_in_eating (dish_id, eating_id, cnt)
values ('fb262d1a-5d77-4d45-a271-ad78375e6cef', '3b04c377-7ff1-487f-989f-4cea69e54f11', 2),
       ('325030ed-8163-4e6f-87a9-a6ca8ac1df92', '2fb16d24-6ba9-4a6a-99af-fa1b3aeb13ae', 3),
       ('ebe4d182-da23-4238-96b5-6324132fec20', 'b4232eb2-32ee-4e22-97e0-5656ff41b206', 4);