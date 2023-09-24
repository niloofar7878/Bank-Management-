package com.example.BankManagement.modules.account.model;

import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import com.example.BankManagement.modules.customer.model.Customer;
import com.example.BankManagement.modules.transaction.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Account {

    @Id
    @Column(name = "ACCOUNT_ID")
    @SequenceGenerator(name = "ACCT_ID")
    @GeneratedValue(generator = "ACCT_ID")
    private int accountId;

    private long accountNumber;
    private long cardId;
    private double accountBalance;

    @ManyToOne
    private Customer customerInfo;

    public Account(Customer customerInfo, BankBranch bankBranch) {
        this.customerInfo= customerInfo;
        this.bankBranch = bankBranch;
        Date createDateTime = new Date();
    }


    private enum AccountType{
        SAVING_ACCOUNT (1, "saving account with interest"),
        CURRENT_ACCOUNT(2, "current account");

        private final int accountTypeCode;
        private final String accountTypeDesc;

        AccountType(int accountTypeCode, String accountTypeDesc) {
            this.accountTypeCode = accountTypeCode;
            this.accountTypeDesc = accountTypeDesc;
        }

        public int getAccountTypeCode() {
            return accountTypeCode;
        }

        public String getAccountTypeDesc() {
            return accountTypeDesc;
        }
    }

    @JsonIgnore
    @OneToMany
    List<Transaction> transactions;

    @OneToOne(cascade = CascadeType.ALL)
    private BankBranch bankBranch;
    
    private String accountStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;


}
