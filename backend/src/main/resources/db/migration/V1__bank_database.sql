CREATE TABLE Parameter
(
    parameterId BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(200) NOT NULL,
    value       TEXT         NOT NULL,
    PRIMARY KEY (parameterId)
);

CREATE TABLE Classification
(
    classificationId       BIGINT       NOT NULL AUTO_INCREMENT,
    internalId             INT          NOT NULL,
    description            VARCHAR(150) NOT NULL,
    parentClassificationId BIGINT,
    PRIMARY KEY (classificationId)
);

CREATE TABLE User
(
    userId               BIGINT       NOT NULL AUTO_INCREMENT,
    identificationNumber BIGINT       NOT NULL,
    fullName             VARCHAR(200) NOT NULL,
    username             VARCHAR(60)  NOT NULL,
    password             VARCHAR(500) NOT NULL,
    birthday             TIMESTAMP    NOT NULL,
    email                VARCHAR(100) NOT NULL,
    phoneNumber          VARCHAR(10)  NOT NULL,
    userClassificationId BIGINT       NOT NULL,
    PRIMARY KEY (userId)
);

CREATE TABLE AccountTypeData
(
    accountTypeDataId           BIGINT         NOT NULL AUTO_INCREMENT,
    currency                    VARCHAR(3)     NOT NULL,
    initialBalance              DECIMAL(10, 2) NOT NULL,
    exchangeRate                DECIMAL(10, 2) NOT NULL,
    accountTypeClassificationId BIGINT         NOT NULL,
    PRIMARY KEY (accountTypeDataId)
);

CREATE TABLE Account
(
    accountId               BIGINT         NOT NULL AUTO_INCREMENT,
    nameAccount             VARCHAR(100)   NOT NULL,
    associationPin          INT            NOT NULL,
    balance                 DECIMAL(10, 2) NOT NULL,
    accountClassificationId BIGINT         NOT NULL,
    statusClassificationId  BIGINT         NOT NULL,
    notify                  BOOLEAN        NOT NULL,
    PRIMARY KEY (accountId)
);

CREATE TABLE AccountLog
(
    logId                     BIGINT    NOT NULL AUTO_INCREMENT,
    message                   TEXT      NOT NULL,
    datetime                  TIMESTAMP NOT NULL,
    userId                    BIGINT    NOT NULL,
    accountId                 BIGINT    NOT NULL,
    operationClassificationId BIGINT    NOT NULL,
    PRIMARY KEY (logId)
);

CREATE TABLE Transaction
(
    transactionId               BIGINT         NOT NULL AUTO_INCREMENT,
    referenceNumber             BIGINT         NOT NULL,
    accountId                   BIGINT         NOT NULL,
    previousBalance             DECIMAL(10, 2) NOT NULL,
    amount                      DECIMAL(10, 2) NOT NULL,
    datetime                    TIMESTAMP      NOT NULL,
    transactionClassificationId BIGINT         NOT NULL,
    PRIMARY KEY (transactionId)
);

CREATE TABLE CustomerAccount
(
    customerAccountId BIGINT NOT NULL AUTO_INCREMENT,
    userId            BIGINT NOT NULL,
    accountId         BIGINT NOT NULL,
    PRIMARY KEY (customerAccountId)
);

ALTER TABLE Classification
    ADD CONSTRAINT classification_classification_fk
        FOREIGN KEY (parentClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE User
    ADD CONSTRAINT classification_customer_fk
        FOREIGN KEY (userClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE AccountTypeData
    ADD CONSTRAINT classification_accounttype_fk
        FOREIGN KEY (accountTypeClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE Account
    ADD CONSTRAINT classification_account_fk
        FOREIGN KEY (accountClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE Transaction
    ADD CONSTRAINT classification_trannsaction_fk
        FOREIGN KEY (transactionClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE Account
    ADD CONSTRAINT classification_account_fk1
        FOREIGN KEY (statusClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE AccountLog
    ADD CONSTRAINT classification_accountlog_fk
        FOREIGN KEY (operationClassificationId)
            REFERENCES Classification (classificationId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE CustomerAccount
    ADD CONSTRAINT customer_customeraccount_fk
        FOREIGN KEY (userId)
            REFERENCES User (userId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE AccountLog
    ADD CONSTRAINT user_accountlog_fk
        FOREIGN KEY (userId)
            REFERENCES User (userId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE CustomerAccount
    ADD CONSTRAINT account_customeraccount_fk
        FOREIGN KEY (accountId)
            REFERENCES Account (accountId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE Transaction
    ADD CONSTRAINT account_transaction_fk
        FOREIGN KEY (accountId)
            REFERENCES Account (accountId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE AccountLog
    ADD CONSTRAINT account_accountlog_fk
        FOREIGN KEY (accountId)
            REFERENCES Account (accountId)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

-- UserType,AccountType,Status,OperationAccount,TransactionType
INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (100, null, 'USER_TYPE'),
       (200, null, 'ACCOUNT_TYPE'),
       (300, null, 'STATUS'),
       (400, null, 'OPERATION_ACCOUNT'),
       (500, null, 'TRANSACTION_TYPE')
;

-- UserType
INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (101, 1, 'ADMIN'),
       (102, 1, 'CUSTOMER')
;

-- AccountType
INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (201, 2, 'BASIC'),
       (202, 2, 'PREMIUM'),
       (203, 2, 'PLUS')
;

-- Status
INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (301, 3, 'ACTIVE'),
       (302, 3, 'FROZEN'),
       (303, 3, 'INACTIVE')
;

-- OperationAccount
INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (401, 4, 'CREATE'),
       (402, 4, 'UPDATE'),
       (403, 4, 'DELETE'),
       (404, 4, 'FREEZE'),
       (405, 4, 'UNFREEZE')
;

-- TransactionType
INSERT INTO Classification (internalId, parentClassificationId, description)
VALUES (501, 5, 'CREDIT'),
       (502, 5, 'DEBIT')
;

INSERT INTO AccountTypeData (currency, initialBalance, exchangeRate, accountTypeClassificationId)
VALUES ('GTQ', 200.00, 1, 1),
       ('USD', 50.00, 7.50, 2),
       ('EUR', 50.00, 10.00, 3)
;