package org.example.thelooker;

import org.example.thelooker.model.Customer;
import org.example.thelooker.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByUsername_thenReturnCustomer() {
        // Given
        String username = "testUser";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail("test@example.com");
        customer.setPassword("password"); // Note: In real scenarios, ensure passwords are encrypted
        customer.setRole("USER");
        customer.setAddress("123 Main St");
        customerRepository.save(customer);

        // When
        Customer found = customerRepository.findByUsername(username);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo(username);
    }
}
