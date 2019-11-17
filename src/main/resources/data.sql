DELETE FROM users;
DELETE FROM recipes;
DELETE FROM ingredients;

INSERT INTO users(userid, username, password, useremail)
        VALUES  (1, 'test1', 'test1', 'test@test.com'),
                (2, 'test2', 'test2', 'test@test.com'),
                (3, 'test3', 'test3', 'test@test.com'),
                (4, 'test4', 'test4', 'test@test.com'),
                (5, 'test5', 'test5', 'test@test.com');

INSERT INTO recipes(recipeid, recipename, source, category, instructions, userid, imageurl)
        VALUES  (1, 'test1', 'test1', 'test1', 'testestesetesetesetestsetsteetest', 1, 'https://www.fakeurl.com'),
                (2, 'test1', 'test1', 'test1', 'testestesetesetesetestsetsteetest', 1, 'https://www.fakeurl.com'),
                (3, 'test2', 'test2', 'test2', 'testestesetesetesetestsetsteetest', 2, 'https://www.fakeurl.com'),
                (4, 'test2', 'test2', 'test2', 'testestesetesetesetestsetsteetest', 2, 'https://www.fakeurl.com'),
                (5, 'test3', 'test3', 'test3', 'testestesetesetesetestsetsteetest', 3, 'https://www.fakeurl.com'),
                (6, 'test3', 'test3', 'test3', 'testestesetesetesetestsetsteetest', 3, 'https://www.fakeurl.com'),
                (7, 'test4', 'test4', 'test4', 'testestesetesetesetestsetsteetest', 4, 'https://www.fakeurl.com'),
                (8, 'test4', 'test4', 'test4', 'testestesetesetesetestsetsteetest', 4, 'https://www.fakeurl.com'),
                (9, 'test5', 'test5', 'test5', 'testestesetesetesetestsetsteetest', 5, 'https://www.fakeurl.com'),
                (10, 'test5', 'test5', 'test5', 'testestesetesetesetestsetsteetest', 5, 'https://www.fakeurl.com');

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
