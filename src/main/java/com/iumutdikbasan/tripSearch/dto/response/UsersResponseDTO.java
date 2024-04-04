package com.iumutdikbasan.tripSearch.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
//    todo:role
}
