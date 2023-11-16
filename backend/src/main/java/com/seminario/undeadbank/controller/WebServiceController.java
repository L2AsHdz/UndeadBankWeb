package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.dto.TransactionRequestDto;
import com.seminario.undeadbank.dto.TransactionResponseDto;
import com.seminario.undeadbank.dto.ValidateRequestDto;
import com.seminario.undeadbank.dto.ValidateResponseDto;
import com.seminario.undeadbank.service.AccountService;
import com.seminario.undeadbank.service.TransactionService;
import com.seminario.undeadbank.utils.ResponseState;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebServiceController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostMapping("/validateAccount")
    // @PreAuthorize("hasAuthority('PASARELA')")
    public ResponseEntity<ValidateResponseDto> validate(@RequestBody ValidateRequestDto requestDto) {

        if (accountService.validate(requestDto)){
            var response = new ValidateResponseDto(ResponseState.SUCCESS.getCode());
            return ResponseEntity.ok(response);
        }
        else {
            var response = new ValidateResponseDto(ResponseState.ACCOUNT_NOT_FOUND.getCode());
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/transaction")
    // @PreAuthorize("hasAuthority('PASARELA')")
    public ResponseEntity<TransactionResponseDto> transaction(@RequestBody TransactionRequestDto transactionDto) {
        return ResponseEntity.ok(transactionService.doTransaction(transactionDto));
    }
}
