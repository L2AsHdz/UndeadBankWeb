package com.seminario.undeadbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BankException extends RuntimeException {

    private final ProblemDetail detail;

    public BankException(String message) {
        super(message);
        this.detail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
        this.detail.setProperty("datetime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public BankException withStatus(HttpStatus status) {
        this.detail.setStatus(status);
        return this;
    }

    public BankException withTitle(String title) {
        this.detail.setTitle(title);
        return this;
    }
}
