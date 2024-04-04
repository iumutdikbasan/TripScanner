package com.iumutdikbasan.tripSearch.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponseDTO {
    private ZonedDateTime departDate;
    private ZonedDateTime returnDate;
    private String departStation;
    private String returnStation;
}
