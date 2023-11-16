package com.seminario.undeadbank.utils;

import lombok.Getter;

@Getter
public enum ResponseState {
    SUCCESS(100),
    INSUFFICIENT_MONEY(200),
    BAD_REQUEST(300),
    ACCOUNT_NOT_FOUND(400),
    INTERNAL_ERROR(500);

    private final int code;

    private ResponseState(int code) {
        this.code = code;
    }
}
