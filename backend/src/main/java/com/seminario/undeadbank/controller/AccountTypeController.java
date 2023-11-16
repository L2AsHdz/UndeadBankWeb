package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.model.AccountTypeData;
import com.seminario.undeadbank.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-type")
@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AccountTypeData>> getAllAccountTypes() {
        return ResponseEntity.ok(accountTypeService.getAllAccountTypeData());
    }

    @PutMapping("/exchange/{id}/{newExchangeRate}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AccountTypeData> updateExchageRate(@PathVariable Long id, @PathVariable Double newExchangeRate) {
        var updated = accountTypeService.changeExchangeRate(id, newExchangeRate);
        return ResponseEntity.ok(updated);
    }
}
