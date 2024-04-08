package com.iumutdikbasan.tripSearch.dto.request.trip;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripSaveRequestDTO {

    @NotNull(message = "We need a departure date to proceed.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime departDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    @Nullable
    private ZonedDateTime returnDate;

    @NotBlank
    private String departStation;

    @NotBlank
    private String returnStation;

    @NotNull
    private BigDecimal price;
    @NotBlank
    private String departStationId;

    @NotBlank
    private String returnStationId;
}
