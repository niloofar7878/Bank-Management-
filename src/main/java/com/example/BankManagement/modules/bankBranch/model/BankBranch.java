package com.example.BankManagement.modules.bankBranch.model;

import com.example.BankManagement.modules.employee.model.Employee;
import com.example.BankManagement.modules.information.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table
public class BankBranch {

    @Id
    @GeneratedValue
    @Column(name = "BANK_ID")
    private UUID bankId;

    private String branchName;

    private Integer branchCode;

    private Integer routingNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address branchAddress;

    @OneToMany
    private List<Employee> employees;

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
}
