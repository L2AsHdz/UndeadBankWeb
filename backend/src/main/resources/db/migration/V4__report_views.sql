CREATE VIEW AccountTransactionsView AS
SELECT
    t.transactionId,
    t.referenceNumber,
    CA.userId,
    U.fullName AS userFullName,
    t.accountId,
    A.nameAccount,
    t.previousBalance,
    t.amount,
    t.datetime AS operationDate,
    C.description AS operationType
FROM
    Transaction t
        INNER JOIN
    Account A ON t.accountId = A.accountId
        INNER JOIN
    CustomerAccount CA ON A.accountId = CA.accountId
        INNER JOIN
    User U ON CA.userId = U.userId
        INNER JOIN
    Classification C ON t.transactionClassificationId = C.classificationId
ORDER BY
    t.transactionId ASC;


CREATE VIEW FrozenAccountsView AS
SELECT
    CA.userId,
    U.fullName userFullName,
    A.accountId,
    A.nameAccount,
    A.balance,
    C.description accountType
FROM
    Account A
        LEFT JOIN
    Transaction T ON A.accountId = T.accountId
        INNER JOIN
    CustomerAccount CA ON A.accountId = CA.accountId
        INNER JOIN
    User U ON CA.userId = U.userId
        INNER JOIN
    Classification C ON A.accountClassificationId = C.classificationId
WHERE
    T.accountId IS NULL
GROUP BY
    A.accountId;

CREATE VIEW AccountDetailView AS
SELECT
    CA.userId,
    U.fullName AS userFullName,
    A.accountId,
    A.nameAccount,
    A.balance,
    C1.description AS accountType,
    C2.description AS accountStatus,
    AL.datetime AS creationDate
FROM
    CustomerAccount CA
        INNER JOIN
    Account A ON CA.accountId = A.accountId
        INNER JOIN
    User U ON CA.userId = U.userId
        INNER JOIN
    AccountLog AL ON A.accountId = AL.accountId AND AL.operationClassificationId = 14
        INNER JOIN
    Classification C ON AL.operationClassificationId = C.classificationId
        INNER JOIN
    Classification C1 ON A.accountClassificationId = C1.classificationId
        INNER JOIN
    Classification C2 ON A.statusClassificationId = C2.classificationId
;

CREATE VIEW AccountsByStateView AS
SELECT
    1 as Id,
    (SELECT COUNT(*) FROM Account WHERE statusClassificationId = 11) AS cuentasActivas,
    (SELECT COUNT(*) FROM Account WHERE statusClassificationId = 12) AS cuentasCongeladas,
    (SELECT COUNT(*) FROM Account WHERE statusClassificationId = 13) AS cuentasInactivas
;

UPDATE AccountTypeData AccountTypeData SET
    accountTypeClassificationId = 8
WHERE accountTypeClassificationId = 1;

UPDATE AccountTypeData SET
    accountTypeClassificationId = 9
WHERE accountTypeClassificationId = 2;

UPDATE AccountTypeData SET
    accountTypeClassificationId = 10
WHERE accountTypeClassificationId = 3;