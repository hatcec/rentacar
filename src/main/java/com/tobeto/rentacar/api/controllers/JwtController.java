package com.tobeto.rentacar.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JwtController {

//
//    @Autowired
//    private JwtService jwtService;
//
//    @PostMapping({"/authenticate"})
//    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        return jwtService.createJwtToken(jwtRequest);
//    }

//    @Override
//    public CreateUserResponse login(JwtRequest request) {
//        //userBusinessRules.checkUserPresence(request.getEmail());
//        Optional<User> user = userRepository.findByEmail(request.getEmail());
//
//
//        if(!passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
//            //new BusinessException("Wrong Password!");
//            throw new IllegalStateException("Wrong email or password!");
//        }
//
//        try{
//
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//            String token= null;
//
//            if (authentication.isAuthenticated()) {
//                token = jwtService.generateToken(request.getEmail());
//            }else{
//                new BusinessException("Invalid email!");
//            }
//
//            CreateUserResponse response = CreateUserResponse
//                    .builder()
//                    .id(user.get().getId())
//                    .firstName(user.get().getFirstName())
//                    .lastName(user.get().getLastName())
//                    .email(user.get().getEmail())
//                    .role(user.get().getRole().name())
//                    .bareerToken(token)
//                    .build();
//
//            return response;
//        }catch (Exception e){
//            throw new BusinessException("Authentication failed: " + e.getMessage());
//        }
//
//
//    }
}
