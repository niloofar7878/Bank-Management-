package com.example.BankManagement.modules.account.controller;

import com.example.BankManagement.modules.account.AccountDTO;
import com.example.BankManagement.modules.account.service.AccountService;
import com.example.BankManagement.modules.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> registerAccount(@PathVariable int customerId, @PathVariable UUID bankId) {
        return ResponseEntity.ok(accountService.createAccountForCustomer(customerId, bankId));
    }

    @GetMapping("/get/accounts")
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
