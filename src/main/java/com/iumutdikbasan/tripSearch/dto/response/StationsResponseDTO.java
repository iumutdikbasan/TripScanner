package com.iumutdikbasan.tripSearch.dto.response;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StationsResponseDTO {
    private String id;
    private String name;
    private String city;
    private String country;
    private String region;
}
