package com.tobeto.rentacar.config;

import com.tobeto.rentacar.entities.concretes.Role;
import com.tobeto.rentacar.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private  final UserService userService;
    private static final String[] AVAILABLE_URLS = {
            "/v1/api-docs",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/api/v1/brands/**",
            "/api/v1/cars/**",
            "/api/v1/fuels/**",
            "/api/v1/models/**",
            "/api/v1/transmissions/**",
            "/api/v1/rentals/**",
            "/api/v1/users/**",
            "/api/v1/**",
            "/api/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/**")
                        .permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/v1/auth/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/v1/auth/user/**").hasAnyAuthority("USER")
                        .requestMatchers("/api/v1/auth/adminuser/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/signup", "/api/v1/auth/login", "/api/v1/rentals/**", "/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/brands/**", "/api/v1/cars/**", "/api/v1/fuels/**", "/api/v1/models/**", "/api/v1/transmissions/**", "/api/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**", "/api/v1/**").hasAuthority(Role.USER.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**", "/api/v1/**").hasAuthority(Role.USER.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/brands/**", "/api/v1/cars/**", "/api/v1/creditCards/**", "/api/v1/fuels/**", "/api/v1/models/**", "api/v1/rentals/**", "/api/v1/transmissions/**", "/api/v1/users/**", "/api/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/brands/**", "/api/v1/cars/**", "/api/v1/fuels/**", "/api/models/**", "/api/transmissions/**", "/api/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/brands/**", "/api/v1/cars/**", "/api/v1/fuels/**", "/api/v1/models/**", "/api/v1/transmissions/**", "/api/**").hasAuthority(Role.ADMIN.name())

                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
                return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{
        return  config.getAuthenticationManager();
    }
}
