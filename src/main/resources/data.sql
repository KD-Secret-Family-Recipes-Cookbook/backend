-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

DELETE FROM users;
DELETE FROM recipes;
DELETE FROM ingredients;

INSERT INTO users(userid, username, password, useremail)
        VALUES  (1, 'user1', 'user1', 'user1@user.com'),
                (2, 'user2', 'user2', 'user2@user.com'),
                (3, 'user3', 'user3', 'user3@user.com'),
                (4, 'user4', 'user4', 'user4@user.com'),
                (5, 'user5', 'user5', 'user5@user.com');

alter sequence hibernate_sequence restart with 6;

INSERT INTO recipes(recipeid, recipename, source, category, instructions, userid, imageurl)
        VALUES  (1, 'test1', 'test1', 'test1', 'test instructions', 1, 'https://www.fakeurl.com'),
                (2, 'test11', 'test1', 'test1', 'test instructions', 1, 'https://www.fakeurl.com'),
                (3, 'test2', 'test2', 'test2', 'test instructions', 2, 'https://www.fakeurl.com'),
                (4, 'test22', 'test2', 'test2', 'test instructions', 2, 'https://www.fakeurl.com'),
                (5, 'test3', 'test3', 'test3', 'test instructions', 3, 'https://www.fakeurl.com'),
                (6, 'test33', 'test3', 'test3', 'test instructions', 3, 'https://www.fakeurl.com'),
                (7, 'test4', 'test4', 'test4', 'test instructions', 4, 'https://www.fakeurl.com'),
                (8, 'test44', 'test4', 'test4', 'test instructions', 4, 'https://www.fakeurl.com'),
                (9, 'test5', 'test5', 'test5', 'test instructions', 5, 'https://www.fakeurl.com'),
                (10, 'test55', 'test5', 'test5', 'test instructions', 5, 'https://www.fakeurl.com');

alter sequence hibernate_sequence restart with 11;

INSERT INTO ingredients(ingredientid, ingredientname, quantity, measurement, recipeid)
        VALUES  (1, 'test1', 56, 'testmeasurement', 1),
                (2, 'test1', 56, 'testmeasurement', 1),
                (3, 'test1', 56, 'testmeasurement', 2),
                (4, 'test1', 56, 'testmeasurement', 2),
                (5, 'test1', 56, 'testmeasurement', 3),
                (6, 'test1', 56, 'testmeasurement', 3),
                (7, 'test1', 56, 'testmeasurement', 4),
                (8, 'test1', 56, 'testmeasurement', 4),
                (9, 'test1', 56, 'testmeasurement', 5),
                (10, 'test1', 56, 'testmeasurement', 5),
                (11, 'test1', 56, 'testmeasurement', 6),
                (12, 'test1', 56, 'testmeasurement', 6),
                (13, 'test1', 56, 'testmeasurement', 7),
                (14, 'test1', 56, 'testmeasurement', 7),
                (15, 'test1', 56, 'testmeasurement', 8),
                (16, 'test1', 56, 'testmeasurement', 8),
                (17, 'test1', 56, 'testmeasurement', 9),
                (18, 'test1', 56, 'testmeasurement', 9),
                (19, 'test1', 56, 'testmeasurement', 10),
                (20, 'test1', 56, 'testmeasurement', 10);


alter sequence hibernate_sequence restart with 21;