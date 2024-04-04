package com.iumutdikbasan.tripSearch.dto.request.station;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDeleteRequestDTO {
    @NotBlank
    private String id;
}
