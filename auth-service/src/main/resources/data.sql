INSERT INTO role (ID, NAME)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (ID, NAME)
VALUES (2, 'ROLE_MERCHANT');

INSERT INTO bank (ID, NAME, URL)
VALUES (1, 'UniCredit', 'http://localhost:8080/api/v1/unicredit');

INSERT INTO users (ID, ACTIVATED, PASSWORD, USERNAME, ROLE_ID)
VALUES (0, true, '$2a$10$i9gbDSXwPUh99J5Vw9zIveeDdTduv/ZzN3ydURP0pkHDpckqABpOi', 'admin', 1);