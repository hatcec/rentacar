package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.dtos.AuthenticationRequest;
import com.tobeto.rentacar.business.dtos.AuthenticationResponse;
import com.tobeto.rentacar.business.dtos.SignupRequest;
import com.tobeto.rentacar.business.dtos.UserDto;
import com.tobeto.rentacar.business.dtos.requests.RefreshTokenRequest;
import com.tobeto.rentacar.business.dtos.requests.SignUpRequest;
import com.tobeto.rentacar.business.dtos.requests.SigninRequest;
import com.tobeto.rentacar.business.dtos.responses.JwtAuthenticationResponse;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.User;
import com.tobeto.rentacar.services.AuthenticationService;
import com.tobeto.rentacar.services.JwtService;
import com.tobeto.rentacar.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> createCustomer(@RequestBody AuthenticationResponse authenticationResponse) {
        return ResponseEntity.ok(authService.createCustomer(authenticationResponse));
//        if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Email already exist. Try again with another email");
//
//        AuthenticationResponse createdUserDto = authService.createCustomer(signupRequest);
//        if (createdUserDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationResponse authenticationResponse){
        return ResponseEntity.ok(authService.login(authenticationResponse));
    }
//    public AuthenticationResponse createAuthenticationToken(
//            @RequestBody AuthenticationRequest authenticationRequest
//    ) throws
//            BadCredentialsException,
//            DisabledException,
//            UsernameNotFoundException {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Incorrect username or password.");
//        }
//        //final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
//
//    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody AuthenticationResponse req){
        return ResponseEntity.ok(authService.refreshToken(req));
    }

    @GetMapping(value = "/admin/get-all-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<AuthenticationResponse> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(authService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<AuthenticationResponse> updateUser(@PathVariable Integer userId, @RequestBody User reqres){
        return ResponseEntity.ok(authService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<AuthenticationResponse> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        AuthenticationResponse response = authService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<AuthenticationResponse> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(authService.deleteUser(userId));
    }

}
