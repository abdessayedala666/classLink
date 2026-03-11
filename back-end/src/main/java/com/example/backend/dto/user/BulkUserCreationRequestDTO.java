package com.example.backend.dto.user;


import org.springframework.web.multipart.MultipartFile;

import com.example.backend.models.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BulkUserCreationRequestDTO {
    @NotNull
    private MultipartFile file ;
    @NotBlank 
    private Role role ;
    public MultipartFile getFile() {
        return file;
    }
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

}
