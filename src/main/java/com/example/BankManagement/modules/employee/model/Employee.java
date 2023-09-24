package com.example.BankManagement.modules.employee.model;

import com.example.BankManagement.modules.information.Address;
import com.example.BankManagement.modules.information.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private int employeeId;

    private String firstName;
    private String lastName;
    private String fatherName;
    private int age;
    private double salary;
    private String designation;
    private Date hireDate;
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address employeeAddress;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact employeeDetails;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
}
