package com.example.BankManagement.modules.employee;

import com.example.BankManagement.modules.information.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private int age;
    private double salary;
    private String designation;
    private Date hireDate;
    private Address employeeAddress;
}
