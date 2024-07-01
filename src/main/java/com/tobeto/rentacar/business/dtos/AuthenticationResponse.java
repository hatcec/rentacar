package com.tobeto.rentacar.business.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tobeto.rentacar.entities.concretes.Role;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {
    private int statusCode;
    private String error;
    private int id;
    private String message;
    private String jwt;
    private String refreshToken;
    private String expirationTime;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private Role role;
    private User users;
    private List<User> ourUsersList;

}
