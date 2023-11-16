package com.seminario.undeadbank.dto;

import java.time.LocalDateTime;

public record TransactionResponseDto(Long referenceNumber, int status, String message, String datetime) {

    public TransactionResponseDto(Long referenceNumber, int status, String message) {
        this(
                referenceNumber,
                status,
                message,
                LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
