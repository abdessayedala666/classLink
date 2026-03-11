package com.example.backend.security;

import com.example.backend.models.User;
import com.example.backend.models.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the user's role as a GrantedAuthority with ROLE_ prefix
        return Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Use fullName as the username
        return user.getFullName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Custom method to get email
    public String getEmail() {
        return user.getEmail();
    }

    // Get the user's ID
    public Long getId() {
        return user.getId();
    }

    // Get the user's role
    public Role getRole() {
        return user.getRole();
    }

    // Get the underlying User entity
    public User getUser() {
        return user;
    }

    // Check if this is the user's first login
    public Boolean isFirstLogin() {
        return user.getFirstLogin();
    }

    // Get school ID if exists
    public Long getSchoolId() {
        return user.getSchool() != null ? user.getSchool().getId() : null;
    }
}
