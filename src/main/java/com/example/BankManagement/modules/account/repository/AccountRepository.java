package com.example.BankManagement.modules.account.repository;

import com.example.BankManagement.modules.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByAccountNumber (long accountNumber);


}
