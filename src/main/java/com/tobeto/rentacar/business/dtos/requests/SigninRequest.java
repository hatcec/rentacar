package com.tobeto.rentacar.business.dtos.requests;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
