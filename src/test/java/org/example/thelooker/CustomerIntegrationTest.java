package org.example.thelooker;

import org.example.thelooker.model.Customer;
import org.example.thelooker.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser(username = "admin", roles = {"ROLE_ADMIN"})
    public void testCustomerAccess() throws Exception {
        // Setup data
        Customer customer = new Customer();
        customer.setUsername("testUser");
        customer.setPassword("testPass");
        customer.setEmail("test@example.com");
        customer.setAddress("123 Test Street");
        customer.setRole("ROLE_USER");
        customerRepository.save(customer);

        // Perform request
        mockMvc.perform(get("/yourProtectedUrl"))
                .andExpect(status().isOk());

        // Additional assertions to verify the results
    }
}
