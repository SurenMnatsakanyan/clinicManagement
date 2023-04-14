package com.example.clinicmanagement.service;
import com.example.clinicmanagement.Excpetion.UserRegisteredException;
import com.example.clinicmanagement.model.AuthenticationRequest;
import com.example.clinicmanagement.model.AuthenticationResponse;
import com.example.clinicmanagement.model.RegisterRequest;
import com.example.clinicmanagement.repository.UserRepository;
import com.example.clinicmanagement.user.AppUser;
import com.example.clinicmanagement.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) throws UserRegisteredException {
        Optional<AppUser> appUser = userRepository.findByEmail(request.getEmail());
        if(appUser.isPresent())
            throw  new UserRegisteredException("User with this " + request.getEmail() + " exists");
        var user = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    var user = userRepository.findByEmail(request.getEmail()).orElseThrow(
            () -> new UsernameNotFoundException("User not found "));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
