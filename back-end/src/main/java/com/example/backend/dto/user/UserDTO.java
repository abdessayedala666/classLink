package com.example.backend.dto.user;

import com.example.backend.models.enums.Gender;
import com.example.backend.models.enums.Role;

import java.time.LocalDate;

public class UserDTO {

    private Long id;
    private String fullName;
    private String email;
    private LocalDate birthday;
    private Gender gender;
    private Role role;
    private Boolean firstLogin;
    private Long schoolId;

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String fullName, String email, LocalDate birthday, Gender gender, Role role, Boolean firstLogin, Long schoolId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.role = role;
        this.firstLogin = firstLogin;
        this.schoolId = schoolId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
