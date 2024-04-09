package org.example.thelooker.service;

import org.example.thelooker.dto.CustomerDto;
import org.example.thelooker.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    Optional<Customer> findByUsername(String username);
    List<Customer> findAllCustomers();
    void updateCustomer(Long id, CustomerDto customerDto);
    void deleteCustomer(Long id);
}

