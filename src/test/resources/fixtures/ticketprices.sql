INSERT INTO MOVIE (ID, DESCRIPTION, LENGTH, MINAGE, TITLE) VALUES (1, 'description', 120, 12, 'title');
INSERT INTO MOVIE_ACTORS (MOVIE_ID, ACTORS) VALUES (1, 'Kowalski');
INSERT INTO MOVIE_GENRES (MOVIE_ID, GENRES) VALUES (1, 'drama');
INSERT INTO CINEMA (ID, CITY, NAME) VALUES (3, 'Lublin', 'plaza');
INSERT INTO TICKETPRICE VALUES (20, 25, 'regular');
INSERT INTO TICKETPRICE VALUES (10, 15, 'student');
INSERT INTO MOVIE_TICKETPRICE VALUES (1, 20);
INSERT INTO MOVIE_TICKETPRICE VALUES (1, 10);
INSERT INTO SHOW (ID, CINEMA_ID, MOVIE_ID, "DATE", "TIME") VALUES (1, 3, 1, '2016-09-20', '13:05:00');