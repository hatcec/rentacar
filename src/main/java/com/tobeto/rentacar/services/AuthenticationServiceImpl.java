package com.tobeto.rentacar.services;

import com.tobeto.rentacar.business.dtos.AuthenticationRequest;
import com.tobeto.rentacar.business.dtos.AuthenticationResponse;
import com.tobeto.rentacar.business.dtos.SignupRequest;
import com.tobeto.rentacar.business.dtos.UserDto;
import com.tobeto.rentacar.business.dtos.requests.RefreshTokenRequest;
import com.tobeto.rentacar.business.dtos.requests.SignUpRequest;
import com.tobeto.rentacar.business.dtos.requests.SigninRequest;
import com.tobeto.rentacar.business.dtos.responses.JwtAuthenticationResponse;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.concretes.Role;
import com.tobeto.rentacar.entities.concretes.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements  AuthenticationService{
    private final UserRepository userRepository;
    @Autowired
    private JwtService jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private ModelMapperService modelMapperService;

    @Override
    public AuthenticationResponse createCustomer(AuthenticationResponse authenticationResponse) {

        AuthenticationResponse response=new AuthenticationResponse();
        try{
        User user = new User();
        user.setEmail(authenticationResponse.getEmail());
        user.setFirstName(authenticationResponse.getFirstName());
        user.setLastName(authenticationResponse.getLastName());
        user.setPassword(passwordEncoder.encode(authenticationResponse.getPassword()));
       // user.setRole(signupRequest.getRole());
        user.setCity(authenticationResponse.getCity());
        user.setRole(Role.USER);

        User createdCustomer = userRepository.save(user);
        if (createdCustomer.getId()>0) {
            response.setUsers((createdCustomer));
            response.setMessage("User Saved Successfully");
            response.setStatusCode(200);
        }

    }catch (Exception e){
        response.setStatusCode(500);
        response.setError(e.getMessage());
    }
        return response;
//        UserDto createdUserDto = new UserDto();
//        createdUserDto.setId(createdCustomer.getId());
//        createdUserDto.setFirstName(createdCustomer.getFirstName());
//        createdUserDto.setLastName(createdCustomer.getLastName());
//        createdUserDto.setEmail(createdCustomer.getEmail());
//        createdUserDto.setUserRole(createdCustomer.getRole());
//        return createdUserDto;
    }

    public AuthenticationResponse login(AuthenticationResponse authenticationResponse1){
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationResponse1.getEmail(), authenticationResponse1.getPassword()));
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException("Incorrect username or password.");
//        }
//        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationResponse1.getEmail());
//        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
//        final String jwt = jwtService.generateToken(userDetails);
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//        String refreshToken=jwtUtils.generateRefreshToken(new HashMap<>(), optionalUser.get());
//        if (optionalUser.isPresent()) {
//            authenticationResponse.setJwt(jwt);
//            authenticationResponse.setStatusCode(200);
//            authenticationResponse.setId(optionalUser.get().getId());
//            authenticationResponse.setRole(optionalUser.get().getRole());
//
//            authenticationResponse.setRefreshToken(refreshToken);
//            authenticationResponse.setExpirationTime("24Hrs");
//            authenticationResponse.setMessage("succesfully logged in");
//        }
//        return authenticationResponse;
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try{
        User user = userRepository.findByEmail(authenticationResponse1.getEmail()).orElseThrow();
        String jwt = jwtUtils.generateToken(user);
        String refreshToken=jwtUtils.generateRefreshToken(new HashMap<>(), user);


//        if (optionalUser.isPresent()) {
        authenticationResponse.setStatusCode(200);
        authenticationResponse.setJwt(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        authenticationResponse.setExpirationTime("24Hrs");
        authenticationResponse.setId(Integer.valueOf(user.getId()));//response da long -> int yaptım
        authenticationResponse.setRole(user.getRole());
        System.out.println(user.getRole());
        authenticationResponse.setMessage("succesfully logged in");
        //}

        }catch (Exception e){
            authenticationResponse.setStatusCode(500);
            authenticationResponse.setMessage(e.getMessage());
        }
        return authenticationResponse;

    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public AuthenticationResponse refreshToken(AuthenticationResponse refreshTokenReqiest){
        AuthenticationResponse response = new AuthenticationResponse();
        try{
            String ourEmail = jwtUtils.extractUserName(refreshTokenReqiest.getJwt());
            User users = userRepository.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getJwt(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setJwt(jwt);
                response.setRefreshToken(refreshTokenReqiest.getJwt());
                response.setExpirationTime("24Hr");
                response.setId(Integer.valueOf(refreshTokenReqiest.getId()));//response da long -> int yaptım
                response.setRole(refreshTokenReqiest.getRole());
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public AuthenticationResponse  getAllUsers() {
        AuthenticationResponse reqRes = new AuthenticationResponse();

        try {
            List<User> result = userRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");

                // Logging ourUsersList content
                System.out.println("Status Code: {}"+ reqRes.getStatusCode());
                System.out.println("Message: {}"+ reqRes.getMessage());
                System.out.println("Our Users List: {}"+ reqRes.getOurUsersList());

                // Detaylı yazdırma
                for (User user : reqRes.getOurUsersList()) {
                    System.out.println("User: {}"+ user);
                }
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
        }




    public AuthenticationResponse getUsersById(Integer id) {
        AuthenticationResponse reqRes = new AuthenticationResponse();
        try {
            User usersById = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setUsers(usersById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }



    public AuthenticationResponse deleteUser(Integer userId) {
        AuthenticationResponse reqRes = new AuthenticationResponse();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    public AuthenticationResponse updateUser(Integer userId, User updatedUser) {
        AuthenticationResponse reqRes = new AuthenticationResponse();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setFirstName(updatedUser.getFirstName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepository.save(existingUser);
                reqRes.setUsers( savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public AuthenticationResponse getMyInfo(String email){
        AuthenticationResponse reqRes = new AuthenticationResponse();
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setUsers( userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;

    }
}