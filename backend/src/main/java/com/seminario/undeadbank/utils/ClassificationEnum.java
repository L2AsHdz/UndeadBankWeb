package com.seminario.undeadbank.utils;

import com.seminario.undeadbank.exception.BankException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ClassificationEnum {

    USER_TYPE(100),
        ADMIN(101),
        CUSTOMER(102),
    ACCOUNT_TYPE(200),
        BASIC(201),
        PREMIUM(202),
        PLUS(203),
    STATUS(300),
        ACTIVE(301),
        FROZEN(302),
        INACTIVE(303),
    OPERATION_ACCOUNT_TYPE(400),
        CREATE(401),
        UPDATE(402),
        DELETE(403),
        FREEZE(404),
        UNFREEZE(405),
    TRANSACTION_TYPE(500),
        CREDIT(501),
        DEBIT(502);

    private final long id;

    private ClassificationEnum(long id) {
        this.id = id;
    }

    public static ClassificationEnum getById(long id) {
        return Arrays.stream(ClassificationEnum.values())
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BankException("No enum constant with id " + id));
    }
}
