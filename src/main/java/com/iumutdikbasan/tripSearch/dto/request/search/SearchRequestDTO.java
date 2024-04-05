package com.iumutdikbasan.tripSearch.dto.request.search;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDTO {
    @NotNull
    private ZonedDateTime departDate;
    @Nullable
    private ZonedDateTime returnDate;
    @NotBlank
    private String departStationName;
    @NotBlank
    private String returnStationName;
}
