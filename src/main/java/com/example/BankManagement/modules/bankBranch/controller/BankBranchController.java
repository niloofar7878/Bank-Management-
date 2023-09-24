package com.example.BankManagement.modules.bankBranch.controller;

import com.example.BankManagement.modules.bankBranch.BankBranchDTO;
import com.example.BankManagement.modules.bankBranch.model.BankBranch;
import com.example.BankManagement.modules.bankBranch.service.BankBranchService;
import com.example.BankManagement.modules.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bank-branch")
public class BankBranchController {

    private final
    BankBranchService bankBranchService;

    public BankBranchController(BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

    @PostMapping("/save")
    public ResponseEntity<BankBranchDTO> saveBankBranch(@RequestBody BankBranch bank) {
        BankBranchDTO savedBankBranch = bankBranchService.saveBankBranch(bank);
        return new ResponseEntity<>(savedBankBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{bankId}")
    public ResponseEntity<String> deleteBankBranch(@PathVariable UUID bankId) {
        bankBranchService.deleteBankBranch(bankId);
        return new ResponseEntity<>("Bank Branch has been deleted", HttpStatus.OK);
    }

    @PostMapping("/add-employee/{bankId}/{employeeId}")
    public ResponseEntity<String> addEmployeeToBankBranch(@PathVariable UUID bankId, @PathVariable int employeeId) {
        bankBranchService.addEmployeeToBankBranch(bankId, employeeId);
        return new ResponseEntity<>("Employee has been added to Bank Branch", HttpStatus.OK);
    }

    @GetMapping("/employees/{bankId}")
    public ResponseEntity<List<Employee>> getEmployeesByBankId(@PathVariable UUID bankId) {
        List<Employee> employees = bankBranchService.getEmployeesByBankId(bankId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

}
