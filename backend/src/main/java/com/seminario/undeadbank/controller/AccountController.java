package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.Account;
import com.seminario.undeadbank.model.AccountDetailView;
import com.seminario.undeadbank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/list/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AccountDetailView>> getAccountByUserId(@PathVariable Long userId) {
        var accounts = accountService.findAccountsByUserId(userId);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AccountDetailView>> getAllAccounts() {
        var accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        var account = accountService.findByAccountId(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Account> createAccount(@PathVariable Long userId, @RequestBody Account account) {
        var createdAccount = accountService.save(userId, account);
        return ResponseEntity.ok(createdAccount);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        var updatedAccount = accountService.update(id, account);
        if (updatedAccount == null) throw new BankException("Account not updated");
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteByAccoutId(id);
        return ResponseEntity.noContent().build();
    }
}
