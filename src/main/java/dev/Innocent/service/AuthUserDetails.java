package dev.Innocent.service;

import dev.Innocent.DTO.request.LoginRequest;
import dev.Innocent.DTO.request.UserRequest;
import dev.Innocent.DTO.response.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthUserDetails {
    AuthResponse createUserHandler(UserRequest userRequest) throws Exception;

    AuthResponse signIn(@RequestBody LoginRequest loginRequest);
}
