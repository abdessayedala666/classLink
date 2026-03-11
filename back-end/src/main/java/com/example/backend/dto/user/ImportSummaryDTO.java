package com.example.backend.dto.user;

import org.checkerframework.checker.units.qual.N;

import jakarta.validation.constraints.NotNull;

public class ImportSummaryDTO {

    @NotNull
    private int totalRows ;
    @NotNull
    private int successCount ;
    @NotNull
    private int failedCount ;

    public int getTotalRows() {
        return totalRows;
    }
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
    public int getSuccessCount() {
        return successCount;
    }
    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }
    public int getFailedCount() {
        return failedCount;
    }
    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }
    
    

}
