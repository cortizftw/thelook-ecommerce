package org.example.thelooker;

import org.example.thelooker.controller.AuthController;
import org.example.thelooker.dto.CustomerDto;
import org.example.thelooker.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AuthController authController;

    private CustomerDto customerDto;

    @BeforeEach
    void setUp() {
        // Setup your test data here
        customerDto = new CustomerDto();
        customerDto.setUsername("testUser");
        customerDto.setEmail("test@example.com");
        customerDto.setPassword("password");
    }

    @Test
    void testRegistrationSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = authController.registration(customerDto, bindingResult, model);

        verify(customerService, times(1)).saveCustomer(any(CustomerDto.class));
        assertEquals("redirect:/register?success", viewName);
    }

    @Test
    void testRegistrationFailureDueToValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = authController.registration(customerDto, bindingResult, model);

        verify(customerService, never()).saveCustomer(any(CustomerDto.class));
        assertEquals("register", viewName);
    }
}
