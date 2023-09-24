package com.example.BankManagement.modules.customer.service;

import com.example.BankManagement.modules.customer.CustomerConvertor;
import com.example.BankManagement.modules.customer.CustomerDTO;
import com.example.BankManagement.modules.customer.model.Customer;
import com.example.BankManagement.modules.customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    public final
    CustomerRepository customerRepository;
    private final
    CustomerConvertor customerConvertor;

    public CustomerService(CustomerRepository customerRepository, CustomerConvertor customerConvertor) {
        this.customerRepository = customerRepository;
        this.customerConvertor = customerConvertor;
    }

    public CustomerDTO save(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return customerConvertor.entityToDto(savedCustomer);
    }

    public CustomerDTO updateCustomer(int id, String firstName, String lastName, String fatherName, Long customerNumber, String status) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new EntityNotFoundException("Customer not found!");
        }

        Customer customer = customerOptional.get();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setFatherName(fatherName);
        customer.setCustomerNumber(customerNumber);
        customer.setStatus(status);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerConvertor.entityToDto(updatedCustomer);
    }


    public void delete(int customerId) {
        customerRepository.deleteById(customerId);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerConvertor.customerDtoList(customers);
    }

    public void blockCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setStatus("Blocked");
            customerRepository.save(customer);
        } else {
            throw new EntityNotFoundException("Customer not found for id: " + customerId);
        }
    }

    public void unblockCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setStatus("Active");
            customerRepository.save(customer);
        } else {
            throw new EntityNotFoundException("Customer not found for id: " + customerId);
        }
    }
}