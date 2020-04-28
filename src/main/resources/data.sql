
INSERT INTO USERS (ID, ACTIVE, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (1, true, 'Renata', 'Abreu', 'renata89abreu@gmail.com', '$2a$10$BXrgTIQ.BY9DinOCYQLt9uT4kuNlAu2HXs0G2.Qnf5IeqjzgV0ECG');

INSERT INTO ROLES(ID, ROLE) VALUES (1, 'ADMIN');

INSERT INTO USER_ROLE(USER_ID, ROLE_ID) VALUES (1, 1);

INSERT INTO POSTS (ID, TITLE, BODY, POSTED_AT, USER_ID) VALUES (1, 'First post', 'Hi, this is my first post.', NOW(), 1);

INSERT INTO COMMENTS (ID, BODY, CREATED_AT, POST_ID) VALUES (1, 'Nice post', now(), 1);
INSERT INTO COMMENTS (ID, BODY, CREATED_AT, POST_ID) VALUES (2, 'So sexy!!', now(), 1);