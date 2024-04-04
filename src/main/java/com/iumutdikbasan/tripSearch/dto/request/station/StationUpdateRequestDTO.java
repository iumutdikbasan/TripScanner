package com.iumutdikbasan.tripSearch.dto.request.station;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationUpdateRequestDTO {
    @NotBlank
    @Size(min=3, message = "Please enter a station name with at least 3 characters.")
    private String name;
}
