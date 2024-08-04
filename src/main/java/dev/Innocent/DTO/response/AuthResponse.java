package dev.Innocent.DTO.response;

import dev.Innocent.enums.ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String Title;
    private String message;
    private ROLE role;
}
