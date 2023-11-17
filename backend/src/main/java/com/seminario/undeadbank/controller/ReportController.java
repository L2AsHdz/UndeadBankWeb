package com.seminario.undeadbank.controller;

import com.seminario.undeadbank.dto.TransactionDetailsRequestDto;
import com.seminario.undeadbank.model.AccountDetailView;
import com.seminario.undeadbank.model.AccountTransactionsView;
import com.seminario.undeadbank.model.AccountsByStateView;
import com.seminario.undeadbank.model.FrozenAccountsView;
import com.seminario.undeadbank.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/userTransactions")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<AccountTransactionsView>> getUserTransactions(@RequestBody TransactionDetailsRequestDto requestDto) {
        var result = reportService.getUserTransactions(requestDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/adminTransactions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AccountTransactionsView>> getAdminTransactions(@RequestBody TransactionDetailsRequestDto requestDto) {
        return ResponseEntity.ok(reportService.getTransactions(requestDto));
    }

    @GetMapping("/frozenAccounts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FrozenAccountsView>> getFrozenAccounts() {
        return ResponseEntity.ok(reportService.getFrozenAccounts());
    }

    @GetMapping("/accountDetail/{accountId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AccountDetailView> getAccountDetail(@PathVariable Long accountId) {
        return ResponseEntity.ok(reportService.getAccountDetail(accountId));
    }

    @GetMapping("/accountsByState")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AccountsByStateView> getAccountsByState() {
        return ResponseEntity.ok(reportService.getAccountsByState());
    }

}
