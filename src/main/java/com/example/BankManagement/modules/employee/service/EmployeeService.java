package com.example.BankManagement.modules.employee.service;

import com.example.BankManagement.modules.employee.EmployeeConvertor;
import com.example.BankManagement.modules.employee.EmployeeDTO;
import com.example.BankManagement.modules.employee.model.Employee;
import com.example.BankManagement.modules.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EmployeeService {
    private final
    EmployeeRepository employeeRepository;
    private final
    EmployeeConvertor employeeConvertor;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeConvertor employeeConvertor) {
        this.employeeRepository = employeeRepository;
        this.employeeConvertor = employeeConvertor;
    }

    public EmployeeDTO saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeConvertor.entityToDto(savedEmployee);
    }

    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeConvertor.employeeDtoList(employees);
    }


}
