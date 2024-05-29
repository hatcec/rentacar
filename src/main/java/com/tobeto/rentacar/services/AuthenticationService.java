package com.tobeto.rentacar.services;

import com.tobeto.rentacar.business.dtos.SignupRequest;
import com.tobeto.rentacar.business.dtos.UserDto;
import com.tobeto.rentacar.business.dtos.requests.RefreshTokenRequest;
import com.tobeto.rentacar.business.dtos.requests.SignUpRequest;
import com.tobeto.rentacar.business.dtos.requests.SigninRequest;
import com.tobeto.rentacar.business.dtos.responses.JwtAuthenticationResponse;
import com.tobeto.rentacar.entities.concretes.User;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
