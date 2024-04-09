package org.example.thelooker.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.thelooker.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.thelooker.model.Customer;
import org.example.thelooker.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        // encrypt the password using spring security
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setRole("ROLE_USER");
        customer.setUsername(customerDto.getUsername());
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

        // Update the customer details
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        // Consider whether you want to allow updating the password and role through this method
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setRole(customerDto.getRole());
        customer.setUsername(customerDto.getUsername());

        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }

        customerRepository.deleteById(id);
    }

}
