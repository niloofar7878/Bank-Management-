package com.example.BankManagement.modules.account.service;

import com.example.BankManagement.modules.account.AccountConvertor;
import com.example.BankManagement.modules.account.AccountDTO;
import com.example.BankManagement.modules.account.model.Account;
import com.example.BankManagement.modules.account.repository.AccountRepository;
import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import com.example.BankManagement.modules.bankBranch.repository.BankBranchRepository;
import com.example.BankManagement.modules.customer.model.Customer;
import com.example.BankManagement.modules.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final
    AccountRepository accountRepository;
    private final
    CustomerRepository customerRepository;
    private final
    BankBranchRepository bankBranchRepository;
    private final
    AccountConvertor accountConvertor;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, BankBranchRepository bankBranchRepository, AccountConvertor accountConvertor) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.bankBranchRepository = bankBranchRepository;
        this.accountConvertor = accountConvertor;
    }

    public AccountDTO createAccountForCustomer(int customerId, UUID bankId){
        Customer customer = customerRepository.findById(customerId).get();
        BankBranch bankBranch= bankBranchRepository.findById(bankId).get();
        Account account= new Account(customer, bankBranch);
        return accountConvertor.entityToDto(accountRepository.save(account));
    }

    public Account getById(int accountId){
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isPresent()){
            return accountOptional.get();
        } else {
            throw new EntityNotFoundException("Account with id: "+ accountId + " does not exist.");
        }
    }

    public List<AccountDTO> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDTOS= accountConvertor.dtoList(accounts);
        return accountDTOS;
    }

}
