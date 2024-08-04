package dev.Innocent.DTO.request;

import dev.Innocent.enums.ROLE;
import dev.Innocent.model.Property;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private ROLE role = ROLE.ROLE_USER;

}
