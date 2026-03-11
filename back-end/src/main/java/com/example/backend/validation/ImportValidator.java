package com.example.backend.validation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ImportValidator {
    private static final Set<String> REQUIRED_COLUMNS = 
        Set.of("email" , "fullname " , "birthday", "gender" );
    
    public static void validateHeaders(List<String> fileHeaders){
        if(fileHeaders == null || fileHeaders.isEmpty()){
            throw new IllegalArgumentException("File must have headers");
        }
        Set<String> normalizedHeaders = fileHeaders.stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toSet()) ;
    
        List<String> missingColumns = REQUIRED_COLUMNS.stream()
                    .filter(required -> !normalizedHeaders.contains(required))
                    .sorted()
                    .toList();
        if(!missingColumns.isEmpty()){
            throw new IllegalArgumentException("Missing required columns: " + String.join(", ", missingColumns));
        }
    }
}
