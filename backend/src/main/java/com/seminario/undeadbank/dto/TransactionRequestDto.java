package com.seminario.undeadbank.dto;

public record TransactionRequestDto(
        Long sourceAccount,
        Long destinationAccount,
        Long paymentAccount,
        Double paymentPercentage,
        Double amount) {

    public Double totalAmount() {
        return amount() + commission();
    }

    public double commission() {
        return amount() * paymentPercentage();
    }
}
