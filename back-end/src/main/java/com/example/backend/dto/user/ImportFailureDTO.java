package com.example.backend.dto.user;

import jakarta.validation.constraints.NotNull;

public class ImportFailureDTO {

    @NotNull
    private int rowNumber ;
    @NotNull
    private String errorMessage ;

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
