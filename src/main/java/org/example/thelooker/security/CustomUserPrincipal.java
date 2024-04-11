package org.example.thelooker.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserPrincipal extends User {
    private final Long customerId;

    public CustomUserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, Long customerId) {
        super(username, password, authorities);
        this.customerId = customerId;
    }

}
