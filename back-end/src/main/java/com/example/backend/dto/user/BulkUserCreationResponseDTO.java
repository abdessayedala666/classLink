package com.example.backend.dto.user;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class BulkUserCreationResponseDTO {

    @NotNull
    private List<ImportSummaryDTO> summary ;
    @NotNull
    private List<ImportSuccessDTO> successes ;
    @NotNull
    private List<ImportFailureDTO> failures ;
    public List<ImportSummaryDTO> getSummary() {
        return summary;
    }
    public void setSummary(List<ImportSummaryDTO> summary) {
        this.summary = summary;
    }
    public List<ImportSuccessDTO> getSuccesses() {
        return successes;
    }
    public void setSuccesses(List<ImportSuccessDTO> successes) {
        this.successes = successes;
    }
    public List<ImportFailureDTO> getFailures() {
        return failures;
    }
    public void setFailures(List<ImportFailureDTO> failures) {
        this.failures = failures;
    }
    

}
