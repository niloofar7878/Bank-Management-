package com.example.BankManagement.modules.transaction.repository;

import com.example.BankManagement.modules.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    public Optional<List<Transaction>> findByAccountNumber(Long accountNumber);

    List<Transaction> findBySourceAccount_AccountNumber(Long cardId);

    List<Transaction> findByDestinationAccount_AccountNumber(Long cardId);
}
