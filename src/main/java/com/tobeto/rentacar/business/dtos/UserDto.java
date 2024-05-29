package com.tobeto.rentacar.business.dtos;

import com.tobeto.rentacar.entities.concretes.Role;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role userRole;


}
