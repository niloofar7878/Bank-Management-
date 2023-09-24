package com.example.BankManagement.modules.information;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "ADDR_ID")
    private UUID id;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}
