package dev.Innocent.controller;

import dev.Innocent.DTO.request.LoginRequest;
import dev.Innocent.DTO.request.UserRequest;
import dev.Innocent.DTO.response.AuthResponse;
import dev.Innocent.service.AuthUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthUserDetails authUserDetails;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody @Valid UserRequest userRequest) throws Exception {
        return ResponseEntity.ok(authUserDetails.createUserHandler(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authUserDetails.signIn(loginRequest));
    }
}
