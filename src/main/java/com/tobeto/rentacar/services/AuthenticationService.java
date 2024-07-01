package com.tobeto.rentacar.services;

import com.tobeto.rentacar.business.dtos.AuthenticationRequest;
import com.tobeto.rentacar.business.dtos.AuthenticationResponse;
import com.tobeto.rentacar.business.dtos.SignupRequest;
import com.tobeto.rentacar.business.dtos.UserDto;
import com.tobeto.rentacar.business.dtos.requests.RefreshTokenRequest;
import com.tobeto.rentacar.business.dtos.requests.SignUpRequest;
import com.tobeto.rentacar.business.dtos.requests.SigninRequest;
import com.tobeto.rentacar.business.dtos.responses.JwtAuthenticationResponse;
import com.tobeto.rentacar.entities.concretes.User;
import org.springframework.stereotype.Service;


public interface AuthenticationService {
    AuthenticationResponse createCustomer(AuthenticationResponse authenticationResponse);
    AuthenticationResponse login(AuthenticationResponse authenticationResponse );
    boolean hasCustomerWithEmail(String email);
    AuthenticationResponse refreshToken(AuthenticationResponse refreshTokenReqiest);
    AuthenticationResponse getAllUsers();
    AuthenticationResponse getUsersById(Integer id);
    AuthenticationResponse deleteUser(Integer userId);
    AuthenticationResponse updateUser(Integer userId, User updatedUser);
    AuthenticationResponse getMyInfo(String email);
}
