package com.iumutdikbasan.tripSearch.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequestDTO {
    @NotBlank(message = "First Name cannot be empty.")
    @Size(min=2, message = "First name must be at least 2 characters")
    private String firstname;
    @NotBlank(message = "Last Name cannot be empty.")
    @Size(min=2, message = "First name must be at least 2 characters")
    private String lastname;

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one numeric character")
    private String password;

}
