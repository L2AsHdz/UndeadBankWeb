package com.seminario.undeadbank.dto;

public record TransactionDetailsRequestDto(Long userId,
                                          Long accountId,
                                          String startDate,
                                          String endDate) {
}
