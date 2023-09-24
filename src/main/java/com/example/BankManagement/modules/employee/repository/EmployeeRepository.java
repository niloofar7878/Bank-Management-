package com.example.BankManagement.modules.employee.repository;

import com.example.BankManagement.modules.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
