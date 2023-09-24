package com.example.BankManagement.modules.bankBranch.repository;

import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankBranchRepository extends JpaRepository<BankBranch, UUID> {
}
