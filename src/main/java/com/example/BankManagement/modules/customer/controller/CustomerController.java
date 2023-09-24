package com.example.BankManagement.modules.customer.controller;

import com.example.BankManagement.modules.customer.CustomerConvertor;
import com.example.BankManagement.modules.customer.CustomerDTO;
import com.example.BankManagement.modules.customer.model.Customer;
import com.example.BankManagement.modules.customer.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final
    CustomerService customerService;
    private final
    CustomerConvertor customerConvertor;

    public CustomerController(CustomerService customerService, CustomerConvertor customerConvertor) {
        this.customerService = customerService;
        this.customerConvertor = customerConvertor;
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
        return ResponseEntity.ok(customerConvertor.entityToDto(customer));
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found!"));

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setFatherName(customerDTO.getFatherName());
        customer.setCustomerNumber(customerDTO.getCustomerNumber());
        customer.setStatus(customerDTO.getStatus());

        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO.getFirstName(),
                customerDTO.getLastName(), customerDTO.getFatherName(), customerDTO.getCustomerNumber(), customerDTO.getStatus());

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/{id}/block")
    public ResponseEntity<Void> blockCustomer(@PathVariable int id) {
        customerService.blockCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/unblock")
    public ResponseEntity<Void> unblockCustomer(@PathVariable int id) {
        customerService.unblockCustomer(id);
        return ResponseEntity.ok().build();
    }

}
