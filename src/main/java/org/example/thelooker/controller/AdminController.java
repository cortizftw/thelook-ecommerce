package org.example.thelooker.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String adminAccess() {
        return "admin";
    }

    @GetMapping("/admin/products")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String adminProducts() {
        return "admin-products";
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String adminUsers() {
        return "admin-users";
    }
}
