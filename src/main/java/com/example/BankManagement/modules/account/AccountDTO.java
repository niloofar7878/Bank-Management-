package com.example.BankManagement.modules.account;

import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import com.example.BankManagement.modules.customer.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private int accountId;
    private long accountNumber;
    private long cardId;
    private double accountBalance;
    private String customerInfo;
    private String bankBranch;
    private String accountStatus;
    private Date createDateTime;

}
