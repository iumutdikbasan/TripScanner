package com.iumutdikbasan.tripSearch.model.concretes;

import com.iumutdikbasan.tripSearch.model.abstracts.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name="trips")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trip extends BaseEntity {

    @Id
    @Column(name="id")
    private String id;

//    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name="depart_date")
    @NotNull(message = "We need a departure date to proceed.")
    private ZonedDateTime departDate;

//    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name="return_date")
    @Nullable
    private ZonedDateTime returnDate;

    @NotNull
    @Column(name="price")
    private BigDecimal price;

    @NotBlank
    @NotEmpty
    @ManyToOne
    @JoinColumn(name="depart_station_id")
    private Station departStation;


    @NotBlank
    @NotEmpty
    @ManyToOne
    @JoinColumn(name="return_station_id")
    private Station returnStation;
}
