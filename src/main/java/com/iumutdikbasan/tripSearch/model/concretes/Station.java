package com.iumutdikbasan.tripSearch.model.concretes;

import com.iumutdikbasan.tripSearch.model.abstracts.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="trips")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Station extends BaseEntity {

    @Id
    @NotBlank
    @Size(min=3,max=40)
    @Column(name="station_code")
    private String id;

    @NotBlank
    @Size(min=3,max=40)
    @Column(name="name")
    private String name;

    @NotBlank
    @Size(min=3,max=40)
    @Column(name="city")
    private String city;

    @NotBlank
    @Size(min=3,max=40)
    @Column(name="country")
    private String country;

    @NotBlank
    @Size(min=3,max=40)
    @Column(name="region")
    private String region;

    @OneToMany(mappedBy = "departStation")
    private List<Station> departingTrips;
    @OneToMany(mappedBy = "returnStation")
    private List<Station> returningTrips;

}
