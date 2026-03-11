package com.example.backend.dto.user;

import org.checkerframework.checker.units.qual.N;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ImportSuccessDTO {

    @NotNull 
    private int rowNumber ;
    @NotEmpty
    private String email ;
    public int getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
