package com.iumutdikbasan.tripSearch.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {
    private String username;
    private String password;
}
