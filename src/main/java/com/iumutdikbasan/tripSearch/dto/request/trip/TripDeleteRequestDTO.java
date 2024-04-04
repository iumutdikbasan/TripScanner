package com.iumutdikbasan.tripSearch.dto.request.trip;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDeleteRequestDTO {
    @NotBlank
    private String id;
}
