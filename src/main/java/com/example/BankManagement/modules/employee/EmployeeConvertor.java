package com.example.BankManagement.modules.employee;

import com.example.BankManagement.modules.employee.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeConvertor {
    public EmployeeDTO entityToDto(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setFatherName(employee.getFatherName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDesignation(employee.getDesignation());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setEmployeeAddress(employee.getEmployeeAddress());
        return employeeDTO;
    }

    public List<EmployeeDTO> employeeDtoList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(entityToDto(employee));
        }
        return employeeDTOs;
    }

}
