package com.example.BankManagement.modules.customer.model;

import com.example.BankManagement.modules.information.Address;
import com.example.BankManagement.modules.information.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "CUST_ID")
    private int id;

    private String firstName;

    private String lastName;

    private String fatherName;

    private Long customerNumber;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address customerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contactDetails;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;

}
