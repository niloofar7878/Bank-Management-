package com.example.BankManagement.modules.transaction;

import com.example.BankManagement.modules.transaction.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionConvertor {
    public TransactionDTO entityToDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setAccountNumber(transaction.getAccountNumber());
        transactionDTO.setTxDateTime(transaction.getTxDateTime());
        transactionDTO.setTxType(transaction.getTxType());
        transactionDTO.setTxAmount(transaction.getTxAmount());
        return transactionDTO;
    }

    public List<TransactionDTO> transactionDTOList(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO transactionDTO = entityToDto(transaction);
            transactionDTOs.add(transactionDTO);
        }
        return transactionDTOs;
    }

}
