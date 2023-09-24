package com.example.BankManagement.modules.employee.controller;

import com.example.BankManagement.modules.employee.EmployeeConvertor;
import com.example.BankManagement.modules.employee.EmployeeDTO;
import com.example.BankManagement.modules.employee.model.Employee;
import com.example.BankManagement.modules.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final
    EmployeeConvertor employeeConvertor;
    private final
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, EmployeeConvertor employeeConvertor) {
        this.employeeService = employeeService;
        this.employeeConvertor = employeeConvertor;
    }
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok(employeeConvertor.entityToDto(employee));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

}
