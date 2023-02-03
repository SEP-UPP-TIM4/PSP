INSERT INTO role (ID, NAME)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (ID, NAME)
VALUES (2, 'ROLE_MERCHANT');

INSERT INTO bank (ID, NAME, URL)
VALUES (1, 'UniCredit', 'http://192.168.43.249:9003/api/v1/unicredit');

INSERT INTO users (ID, ACTIVATED, PASSWORD, USERNAME, ROLE_ID, SECRET)
VALUES (0, true, '$2a$10$i9gbDSXwPUh99J5Vw9zIveeDdTduv/ZzN3ydURP0pkHDpckqABpOi', 'admin', 1, 'HCPFFJUGBMG2K5OCJD5ZN6QCE6GKXNRW');

INSERT INTO users (ID, ACTIVATED, PASSWORD, USERNAME, ROLE_ID, SECRET)
VALUES (1, true, '$2a$10$C1gVEdUXpj5Fn3S1AOR1S.7YuGFDV9sbiRypXM6adfk05ip412E8e', 'petarns99@gmail.com', 2, '53OWV4MVIPLRBJL6RFX6WSX37ZPBQYJA');

INSERT INTO merchant(
    id, api_key, name, user_id)
VALUES ('dfdb5e1e-857c-4fbc-8c12-0ff0eddd12c7', '$2a$10$C45V5acyubGRFJ6p2FKPnOxmLm/JEwoVdHGOrq/yddn8iLUwsYtWW', 'randomcompany', 1);

INSERT INTO payment_method(
    id, bank_payment, name, url)
VALUES (1, true, 'QR-CODE', 'QR-CODE-SERVICE/api/v1/payment');

INSERT INTO payment_method(
    id, bank_payment, name, url)
VALUES (2, true, 'CREDIT-CARD', 'CREDIT-CARD-SERVICE/api/v1/payment');

INSERT INTO payment_method(
    id, bank_payment, name, url)
VALUES (3, false, 'BITCOIN', 'BITCOIN-SERVICE/api/crypto/payment');

INSERT INTO payment_method(
    id, bank_payment, name, url)
VALUES (4, false, 'PAYPAL', 'PAYPAL-SERVICE/api/v1/payment');

INSERT INTO credentials(
    id, password, username, bank_id, payment_method_id, merchant_id)
VALUES (101, 'Xuq6t7Fr/Dg=', '547c7ba9-fc3e-4e48-ab78-6e1ab8e220f0',1, 1, 'dfdb5e1e-857c-4fbc-8c12-0ff0eddd12c7');

INSERT INTO credentials(
    id, password, username, bank_id, payment_method_id, merchant_id)
VALUES (102, 'Xuq6t7Fr/Dg=', '547c7ba9-fc3e-4e48-ab78-6e1ab8e220f0',1, 2, 'dfdb5e1e-857c-4fbc-8c12-0ff0eddd12c7');

INSERT INTO credentials(
    id, password, username, bank_id, payment_method_id, merchant_id)
VALUES (103, '', 'BzoBMWbhvVPUmyBNrtEiMxzjkDSVsx1H91MA7MKp',null, 3, 'dfdb5e1e-857c-4fbc-8c12-0ff0eddd12c7');

INSERT INTO credentials(
    id, password, username, bank_id, payment_method_id, merchant_id)
VALUES (4, 'A1PKEYGr78g77kdwssFGYoG/nGYxB10X4m7v2N7SIMOicwosI+aDVop42++ovPiCsQ07qCHI4DyDwS0QVDAySArkOkbOkNzH64rhUmXTWjSJJSpQl9fUJg==', 'AaAuN_ruosmCReI1qPZoREdP1HwTNigB2OvYpgwEn414vcuuYUooe5PKJsuCk5ESAjLzWh3tfDZVuCd2',null, 4, 'dfdb5e1e-857c-4fbc-8c12-0ff0eddd12c7');