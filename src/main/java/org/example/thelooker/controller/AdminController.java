package org.example.thelooker.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/welcome")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String adminAccess() {
        return "admin welcome page";
    }


}
