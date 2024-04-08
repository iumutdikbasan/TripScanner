package com.iumutdikbasan.tripSearch.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponseDTO {
    private ZonedDateTime departDate;
    private ZonedDateTime returnDate;
    private String departStation;
    private String returnStation;
    private BigDecimal price;
}
