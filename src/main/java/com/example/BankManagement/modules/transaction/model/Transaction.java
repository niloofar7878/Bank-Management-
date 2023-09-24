package com.example.BankManagement.modules.transaction.model;

import com.example.BankManagement.modules.account.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    @Column(name = "TX_ID")
    private UUID transactionId;

    private Long accountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date txDateTime;

    private String txType;

    private Double txAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "transaction_account_source")
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "transaction_account_destination")
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

}
