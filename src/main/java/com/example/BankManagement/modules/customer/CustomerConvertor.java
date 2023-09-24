package com.example.BankManagement.modules.customer;

import com.example.BankManagement.modules.customer.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConvertor {
    public CustomerDTO entityToDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setFatherName(customer.getFatherName());
        dto.setCustomerNumber(customer.getCustomerNumber());
        dto.setStatus(customer.getStatus());
        dto.setCustomerAddress(customer.getCustomerAddress());
        dto.setContactDetails(customer.getContactDetails());
        dto.setCreateDateTime(customer.getCreateDateTime());
        return dto;
    }

    public List<CustomerDTO> customerDtoList(List<Customer> customers) {
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : customers) {
            dtos.add(entityToDto(customer));
        }
        return dtos;
    }

}
