package com.example.BankManagement.modules.transaction.service;

import com.example.BankManagement.modules.account.model.Account;
import com.example.BankManagement.modules.account.repository.AccountRepository;
import com.example.BankManagement.modules.transaction.TransactionConvertor;
import com.example.BankManagement.modules.transaction.TransactionDTO;
import com.example.BankManagement.modules.transaction.model.Transaction;
import com.example.BankManagement.modules.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class TransactionService {
    private final
    AccountRepository accountRepository;
    private final
    TransactionConvertor transactionConvertor;
    private final
    TransactionRepository transactionRepository;

    public TransactionService(TransactionConvertor transactionConvertor, TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionConvertor = transactionConvertor;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public String Card_by_card(Long sourceCardId, Long destinationCardId, Double amount) {
        Account sourceAccount  = accountRepository.findByAccountNumber(sourceCardId);
        if (sourceAccount == null) {
            return "Source card not found";
        }
        Account destinationAccount = accountRepository.findByAccountNumber(destinationCardId);
        if (destinationAccount == null) {
            return "Destination card not found";
        }

        if (sourceAccount.getAccountBalance() < amount) {
            return "Insufficient balance";
        }

        Transaction transaction = new Transaction();
        transaction.setTxType("CARD_BY_CARD");
        transaction.setTxAmount(amount);
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transactionRepository.save(transaction);

        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - amount);
        destinationAccount.setAccountBalance(destinationAccount.getAccountBalance() + amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        return "Transaction successful";

    }


    public TransactionDTO creatTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTxType(transactionDTO.getTxType());
        transaction.setTxAmount(transactionDTO.getTxAmount());
        transaction.setSourceAccount(accountRepository.findByAccountNumber(transactionDTO.getSourceAccount().getAccountNumber()));
        transaction.setDestinationAccount(accountRepository.findByAccountNumber(transactionDTO.getDestinationAccount().getAccountNumber()));
        transactionRepository.save(transaction);
        return transactionConvertor.entityToDto(transaction);

    }


    public String deposit(Long accountNumber, Double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            return "Account not found";
        }

        account.setAccountBalance(account.getAccountBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTxType("DEPOSIT");
        transaction.setTxAmount(amount);
        transaction.setDestinationAccount(account);
        transactionRepository.save(transaction);

        return "Deposit successful";

    }

    public String withdraw(Long accountNumber, Double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            return "Account not found";
        }

        if (account.getAccountBalance() < amount) {
            return "Insufficient balance";
        }

        account.setAccountBalance(account.getAccountBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTxType("WITHDRAW");
        transaction.setTxAmount(amount);
        transaction.setSourceAccount(account);
        transactionRepository.save(transaction);

        return "Withdrawal successful";
    }


    public List<TransactionDTO> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactionConvertor.transactionDTOList(transactions);
    }


    public List<TransactionDTO> getSourceTransactionsByCardId(Long cardId) {
        List<Transaction> transactions = transactionRepository.findBySourceAccount_AccountNumber(cardId);
        return transactionConvertor.transactionDTOList(transactions);
    }


    public List<TransactionDTO> getDestinationTransactionsByCardId(Long cardId) {
        List<Transaction> transactions = transactionRepository.findByDestinationAccount_AccountNumber(cardId);
        return transactionConvertor.transactionDTOList(transactions);
    }


    public TransactionDTO getByUUID(UUID transactionId){
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) {
            return null;
        }
        return transactionConvertor.entityToDto(transaction);
    }

    public List<TransactionDTO> getSourceAndDestinationByCardId (Long cardId){
        List<Transaction> sourceTransactions = transactionRepository.findBySourceAccount_AccountNumber(cardId);
        List<Transaction> destinationTransactions = transactionRepository.findByDestinationAccount_AccountNumber(cardId);
        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(sourceTransactions);
        allTransactions.addAll(destinationTransactions);
        return transactionConvertor.transactionDTOList(allTransactions);
    }
}
