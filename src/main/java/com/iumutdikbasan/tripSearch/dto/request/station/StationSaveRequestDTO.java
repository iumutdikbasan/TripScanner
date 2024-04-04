package com.iumutdikbasan.tripSearch.dto.request.station;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationSaveRequestDTO {
    @NotBlank
    @Size(min=3, message = "Please enter a station name with at least 3 characters.")
    private String name;

    @NotBlank
    @Size(min=3, message = "Please enter a city name with at least 3 characters.")
    private String city;

    @NotBlank
    @Size(min=3, message = "Please enter a country name with at least 3 characters.")
    private String country;

    @NotBlank
    @Size(min=2, message = "Please enter a region name with at least 2 characters.")
    private String region;
}
