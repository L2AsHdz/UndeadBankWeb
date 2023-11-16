INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (103, 1, 'PASARELA')
;

ALTER TABLE Account
    MODIFY COLUMN balance DECIMAL(20,2) NOT NULL;
ALTER TABLE Transaction
    MODIFY COLUMN previousBalance DECIMAL(20,2) NOT NULL;

UPDATE User SET fullName = 'Portal de ventas 2', username = 'ventas2'
WHERE userId = 4
;
UPDATE Account SET balance = 10000000.00;

INSERT INTO User(identificationNumber, fullName, username, password, birthday, email, phoneNumber, userClassificationId)
VALUES
    (123123123127, 'Usuario final 1', 'user1', '123', '1990-01-01 00:00:00', 'asprueba0@gmail.com', '12341234', 7),
    (123123123128, 'Usuario final 2', 'user2', '123', '1990-01-01 00:00:00', 'asprueba0@gmail.com', '44443333', 7),
    (123123123129, 'Usuario final 3', 'user3', '123', '1990-01-01 00:00:00', 'asprueba0@gmail.com', '55556666', 7),
    (123123123110, 'Usuario final 4', 'user4', '123', '1990-01-01 00:00:00', 'asprueba@gmail.com', '77778888', 7),
    (123123123111, 'Usuario final 5', 'user5', '123', '1990-01-01 00:00:00', 'asprueba@gmail.com', '77778888', 7)
;
UPDATE User SET password = '$2a$10$jsOWNNxoZGt0/0qEXMLycuwotmKhnfEMDOGiyTPpFPZ6gIIFFD.Wu';
INSERT INTO Account (nameAccount, associationPin, balance, accountClassificationId, statusClassificationId, notify)
VALUES
    ('user1.basic', 1234, 500000000.00, 8, 11, 0),
    ('user2.basic', 1234, 500000000.00, 8, 11, 0),
    ('user3.basic', 1234, 500000000.00, 8, 11, 0),
    ('user4.basic', 1234, 10.00, 8, 11, 0),
    ('user5.basic', 1234, 100.00, 8, 13, 0)
;

INSERT INTO CustomerAccount (userId, accountId)
VALUES (5, 4);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta user1.basic', NOW(), 5, 4, 14);

INSERT INTO CustomerAccount (userId, accountId)
VALUES (6, 5);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta user2.basic', NOW(), 6, 5, 14);

INSERT INTO CustomerAccount (userId, accountId)
VALUES (7, 6);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta user3.basic', NOW(), 7, 6, 14);

INSERT INTO CustomerAccount (userId, accountId)
VALUES (8, 7);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta user4.basic', NOW(), 8, 7, 14);

INSERT INTO CustomerAccount (userId, accountId)
VALUES (9, 8);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta user5.basic', NOW(), 9, 8, 14);