package org.example.thelooker.controller;

import org.example.thelooker.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.example.thelooker.dto.CustomerDto;
import org.example.thelooker.model.Customer;
import org.example.thelooker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final CustomerService customerService;

    @Autowired
    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                               BindingResult result,
                               Model model) {
        logger.debug("Attempting to register user: {}", customerDto.getUsername());
        Optional<Customer> existingUser = customerService.findByUsername(customerDto.getUsername());

        if (existingUser.isPresent()) {
            logger.warn("Attempted registration with existing username: {}", customerDto.getUsername());
            result.rejectValue("username", null, "There is already an account registered with the same username");
        }

        if (result.hasErrors()) {
            logger.debug("Registration form errors for user: {}", customerDto.getUsername());
            model.addAttribute("customer", customerDto);
            return "register";
        }

        customerService.saveCustomer(customerDto);
        logger.debug("Successfully registered user: {}", customerDto.getUsername());
        return "redirect:/register?success";
    }
}
