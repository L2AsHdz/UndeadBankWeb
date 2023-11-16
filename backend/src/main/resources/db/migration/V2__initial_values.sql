INSERT INTO Parameter (name, value) VALUES('transaction.referenceNumber', '1');
INSERT INTO User(identificationNumber, fullName, username, password, birthday, email, phoneNumber, userClassificationId)
VALUES
    (123123123123, 'Administrador', 'admin', '123', '1990-01-01 00:00:00', 'asprueba0@gmail.com', '12341234', 6),
    (123123123124, 'Portal de pagos', 'pagos', '123', '1990-01-01 00:00:00', 'asprueba0@gmail.com', '44443333', 7),
    (123123123125, 'Portal de ventas', 'ventas', '123', '1990-01-01 00:00:00', 'asprueba0@gmail.com', '55556666', 7),
    (123123123126, 'Usuario de prueba', 'prueba', '123', '1990-01-01 00:00:00', 'asprueba@gmail.com', '77778888', 7)
;
INSERT INTO Account (nameAccount, associationPin, balance, accountClassificationId, statusClassificationId, notify)
VALUES
    ('pagos.basic', 1234, 200.00, 8, 11, 0),
    ('ventas.basic', 1111, 200.00, 8, 11, 0),
    ('prueba.basic', 2222, 200.00, 8, 11, 0)
;
INSERT INTO CustomerAccount (userId, accountId)
VALUES(2, 1);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta pagos.basic', NOW(), 2, 1, 14);

INSERT INTO CustomerAccount (userId, accountId)
VALUES(3, 2);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta ventas.basic', NOW(), 3, 2, 14);

INSERT INTO CustomerAccount (userId, accountId)
VALUES(4, 3);

INSERT INTO AccountLog(message, datetime, userId, accountId, operationClassificationId)
VALUES('Se ha creado la cuenta ventas2.basic', NOW(), 4, 3, 14);