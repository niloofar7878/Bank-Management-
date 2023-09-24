package com.example.BankManagement.modules.bankBranch.service;

import com.example.BankManagement.modules.bankBranch.BankBranchConvertor;
import com.example.BankManagement.modules.bankBranch.BankBranchDTO;
import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import com.example.BankManagement.modules.bankBranch.repository.BankBranchRepository;
import com.example.BankManagement.modules.employee.model.Employee;
import com.example.BankManagement.modules.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BankBranchService {
    private final
    BankBranchRepository bankBranchRepository;
    private final
    BankBranchConvertor bankBranchConvertor;
    private final
    EmployeeRepository employeeRepository;

    public BankBranchService(BankBranchRepository bankBranchRepository, BankBranchConvertor bankBranchConvertor, EmployeeRepository employeeRepository) {
        this.bankBranchRepository = bankBranchRepository;
        this.bankBranchConvertor = bankBranchConvertor;
        this.employeeRepository = employeeRepository;
    }

    public BankBranchDTO saveBankBranch(BankBranch bank){
        bankBranchRepository.save(bank);
        return bankBranchConvertor.entityToDto(bank);
    }

    public void deleteBankBranch(UUID bankId){
        Optional<BankBranch> optionalBankBranch= bankBranchRepository.findById(bankId);
        if (optionalBankBranch.isPresent()){
            BankBranch bankBranch= optionalBankBranch.get();
            bankBranchRepository.delete(bankBranch);
            System.out.println("Bank Branch with id: " + bankId + " has been deleted! ");
        }else {
            throw new EntityNotFoundException("Bank Branch with id: " + bankId + " does not exist! ");
        }
    }


    public void addEmployeeToBankBranch(UUID bankId, int employeeId){
        Optional<BankBranch> bankBranchOptional= bankBranchRepository.findById(bankId);
        if (!bankBranchOptional.isPresent()){
            throw new EntityNotFoundException("Bank Branch not found!");
        }
        BankBranch bankBranch= bankBranchOptional.get();
        Optional<Employee> employeeOptional= employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()){
            throw new EntityNotFoundException("Employee not found!");
        }
        Employee employee= employeeOptional.get();
        bankBranch.addEmployee(employee);
        bankBranchRepository.save(bankBranch);
    }


    public List<Employee> getEmployeesByBankId(UUID bankId){
        List<Employee> employees= new ArrayList<>();
        Optional<BankBranch> bankBranchOptional= bankBranchRepository.findById(bankId);
        if (bankBranchOptional.isPresent()){
            BankBranch bankBranch= bankBranchOptional.get();
            List<Employee> bankBranchEmployees= bankBranch.getEmployees();
            for (Employee employee: bankBranchEmployees){
                employees.add(employee);
            }
        }
        return employees;
    }
}
