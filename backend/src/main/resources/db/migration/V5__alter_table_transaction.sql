UPDATE User SET userClassificationId = 21
WHERE userId = 2;

ALTER TABLE Transaction
ADD COLUMN description VARCHAR(150) NOT NULL DEFAULT 'No description provided';

ALTER TABLE User
    MODIFY COLUMN birthday DATE NOT NULL;