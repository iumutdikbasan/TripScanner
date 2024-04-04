package com.iumutdikbasan.tripSearch.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteRequestDTO {
    @NotBlank
    private Long id;
}
