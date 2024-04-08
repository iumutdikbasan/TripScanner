package com.iumutdikbasan.tripSearch.controller;

import com.iumutdikbasan.tripSearch.business.abstracts.TripBusiness;
import com.iumutdikbasan.tripSearch.common.results.DataResult;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.dto.request.search.SearchRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripUpdateRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.TripsResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@AllArgsConstructor
@NoArgsConstructor
public class TripController {
    @Autowired
    private TripBusiness tripBusiness;

    @GetMapping("/getall")
    private DataResult<List<TripsResponseDTO>> getAll() {
        return this.tripBusiness.getAll();
    }
    @GetMapping("/searchapi")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    private Result searchTrips(@Valid SearchRequestDTO searchRequestDTO) {
        return this.tripBusiness.searchTrips(searchRequestDTO);
    }
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    private Result add(@Valid @RequestBody TripSaveRequestDTO tripSaveRequestDTO){
        return this.tripBusiness.addTrip(tripSaveRequestDTO);
    }
    @DeleteMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    private Result delete(@RequestBody TripDeleteRequestDTO tripDeleteRequestDTO){
        return this.tripBusiness.deleteTrip(tripDeleteRequestDTO);
    }
    @PutMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    private Result update(@RequestBody TripUpdateRequestDTO tripUpdateRequestDTO){
        return this.tripBusiness.updateTrip(tripUpdateRequestDTO);
    }
}
