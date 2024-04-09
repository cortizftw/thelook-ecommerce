package org.example.thelooker.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/welcome")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userAccess() {
        return
                "register";
    }


}
