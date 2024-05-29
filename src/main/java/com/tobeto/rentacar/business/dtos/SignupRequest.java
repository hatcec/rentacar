package com.tobeto.rentacar.business.dtos;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
private int id;
    private String firstName;
    private String lastName;

    private String password;
}
