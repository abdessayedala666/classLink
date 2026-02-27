package com.example.backend.dto.auth;

public class LoginResponseDTO {
    private String email;
    private String name;
    private String role;
    private Boolean firstLogin;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String email, String name, String role, Boolean firstLogin) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.firstLogin = firstLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
