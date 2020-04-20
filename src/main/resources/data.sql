
INSERT INTO AUTHORS (ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD) VALUES (1, 'Renata', 'Abreu', 'renata89abreu@gmail.com', '1234');

INSERT INTO POSTS (ID, TITLE, BODY, POSTED_AT, AUTHOR_ID) VALUES (1, 'First post', 'Hi, this is my first post.', NOW(), 1);

INSERT INTO COMMENTS (ID, BODY, CREATED_AT, POST_ID) VALUES (1, 'Nice post', now(), 1);
INSERT INTO COMMENTS (ID, BODY, CREATED_AT, POST_ID) VALUES (2, 'So sexy!!', now(), 1);