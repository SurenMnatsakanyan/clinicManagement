package com.example.clinicmanagement.controller;
import com.example.clinicmanagement.model.AuthenticationRequest;
import com.example.clinicmanagement.model.AuthenticationResponse;
import com.example.clinicmanagement.model.RegisterRequest;
import com.example.clinicmanagement.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/vi/auth1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody
                                                               RegisterRequest request
    ){

        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                               AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }


}
