package com.tobeto.rentacar.core.utilities.exceptions.problemDetails;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationProblemDetails extends ProblemDetails{
    public ValidationProblemDetails(){
        setTitle("Validation Rule Violation");
        setType("http://mydomain.com/excepiton/business");
        setStatus("400");
        setDetail("Validation Problem");
    }
    private Map<String , String> errors ;
}
