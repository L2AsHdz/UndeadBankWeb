package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.dto.TransactionRequestDto;
import com.seminario.undeadbank.dto.TransactionResponseDto;
import com.seminario.undeadbank.model.Account;
import com.seminario.undeadbank.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
@RequiredArgsConstructor
public class TransactionController {

    private final OperationService operationService;

    @PostMapping("/deposit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TransactionResponseDto> doDeposit(@RequestBody TransactionRequestDto requestDto) {
        return ResponseEntity.ok(operationService.deposit(requestDto));
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TransactionResponseDto> doWithdraw(@RequestBody TransactionRequestDto requestDto) {
        return ResponseEntity.ok(operationService.withdraw(requestDto));
    }
}
