package com.example.backend.dto.user;

import java.time.LocalDate;

import com.example.backend.models.enums.Gender;

public class UserUpdateDTO {
    private String fullName ;
    private String email ;
    private LocalDate birthday ;
    private Gender gender ;
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
        this.birthday = birthday ;
    }
    public Gender getGender() {
        return gender; 
    }
    public void setGender(Gender gender ) {
        this.gender = gender ;  
    }


    

}
