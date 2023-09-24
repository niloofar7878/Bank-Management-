package com.example.BankManagement.modules.bankBranch;

import com.example.BankManagement.modules.employee.model.Employee;
import com.example.BankManagement.modules.information.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankBranchDTO {
    private UUID bankId;
    private String branchName;
    private Integer branchCode;
    private Address branchAddress;
    private Integer routingNumber;
    private List<Employee> employees;
}
