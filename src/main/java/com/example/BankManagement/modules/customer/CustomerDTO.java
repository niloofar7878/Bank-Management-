package com.example.BankManagement.modules.customer;

import com.example.BankManagement.modules.information.Address;
import com.example.BankManagement.modules.information.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Long customerNumber;
    private String status;
    private Address customerAddress;
    private Contact contactDetails;
    private Date createDateTime;
}
