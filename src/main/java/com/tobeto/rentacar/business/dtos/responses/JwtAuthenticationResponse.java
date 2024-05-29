package com.tobeto.rentacar.business.dtos.responses;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;

}
