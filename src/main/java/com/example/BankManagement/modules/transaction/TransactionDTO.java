package com.example.BankManagement.modules.transaction;

import com.example.BankManagement.modules.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {
    private UUID transactionId;
    private Long accountNumber;
    private Date txDateTime;
    private String txType;
    private Double txAmount;
    private Account sourceAccount;
    private Account destinationAccount;
}
