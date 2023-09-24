package com.example.BankManagement.modules.transaction.controller;

import com.example.BankManagement.modules.transaction.TransactionDTO;
import com.example.BankManagement.modules.transaction.model.Transaction;
import com.example.BankManagement.modules.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/card-by-card")
    public String cardByCardTransaction(@RequestParam Long sourceCardId, @RequestParam Long destinationCardId, @RequestParam Double amount) {
        return transactionService.Card_by_card(sourceCardId, destinationCardId, amount);
    }

    @PostMapping("/create")
    public TransactionDTO createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.creatTransaction(transactionDTO);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountNumber, @RequestParam Double amount) {
        return transactionService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountNumber, @RequestParam Double amount) {
        return transactionService.withdraw(accountNumber, amount);
    }

    @GetMapping("/all")
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/source/{cardId}")
    public List<TransactionDTO> getSourceTransactionsByCardId(@PathVariable Long cardId) {
        return transactionService.getSourceTransactionsByCardId(cardId);
    }

    @GetMapping("/destination/{cardId}")
    public List<TransactionDTO> getDestinationTransactionsByCardId(@PathVariable Long cardId) {
        return transactionService.getDestinationTransactionsByCardId(cardId);
    }

    @GetMapping("/{transactionId}")
    public TransactionDTO getTransactionByUUID(@PathVariable UUID transactionId) {
        return transactionService.getByUUID(transactionId);
    }

    @GetMapping("/source-destination/{cardId}")
    public List<TransactionDTO> getSourceAndDestinationTransactionsByCardId(@PathVariable Long cardId) {
        return transactionService.getSourceAndDestinationByCardId(cardId);
    }

}
