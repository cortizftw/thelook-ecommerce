package org.example.thelooker.controller;

import org.example.thelooker.dto.CustomerDto;
import org.example.thelooker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {

    @Autowired
    private CustomerService customerService;

    // Display all users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", customerService.findAllCustomers());
        return "admin-users";
    }

    // Show form to add a new user
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("user", new CustomerDto());
        return "admin-users-add";
    }

    // Save the new or updated user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return "redirect:/admin/users";
    }

    // Show form to edit an existing user
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        model.addAttribute("user", customerDto);
        return "admin-users-edit";
    }

    // Delete a user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin/users";
    }
}
