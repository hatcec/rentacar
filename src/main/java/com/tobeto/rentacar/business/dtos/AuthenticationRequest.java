package com.tobeto.rentacar.business.dtos;

import com.tobeto.rentacar.entities.concretes.Role;
import com.tobeto.rentacar.entities.concretes.User;
import lombok.Data;

import java.util.List;

@Data
public class AuthenticationRequest {
    private int statusCode;
    private String error;
    private String message;
    private String jwt;
    private String refreshToken;
    private Role userRole;
    private int userId;
    private String expirationTime;
    private String firstName;
    private  String lastName;
    private String email;
    private String password;
    private String city;
    private Role role;
    private User users;
    private List<User> ourUsersList;

}
