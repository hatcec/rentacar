package com.tobeto.rentacar.core.utilities.exceptions.problemDetails;

public class BusinessProblemDetails extends ProblemDetails{
    public BusinessProblemDetails(){
        setTitle("Business Rule Violation");
        setType("http://mydomain.com/excepiton/business");
        setStatus("400");

    }
}
