package com.example.BankManagement.modules.customer.repository;

import com.example.BankManagement.modules.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
