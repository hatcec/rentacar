package com.tobeto.rentacar.business.dtos;

import com.tobeto.rentacar.entities.concretes.Role;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Role userRole;
    private Long userId;

}
