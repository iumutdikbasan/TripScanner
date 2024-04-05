package com.iumutdikbasan.tripSearch.controller;

import com.iumutdikbasan.tripSearch.business.abstracts.StationBusiness;
import com.iumutdikbasan.tripSearch.common.results.DataResult;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.dto.request.station.StationDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.station.StationSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.station.StationUpdateRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.StationsResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
@AllArgsConstructor
@NoArgsConstructor
public class StationController {
    @Autowired
    private StationBusiness stationBusiness;

    @GetMapping("/getall")
    private DataResult<List<StationsResponseDTO>> getAll(){
        return this.stationBusiness.getAll();
    }
    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    private Result add(@Valid @RequestBody StationSaveRequestDTO stationSaveRequestDTO){
        return this.stationBusiness.addStation(stationSaveRequestDTO);
    }

    @DeleteMapping
    private Result deleteById(@Valid @RequestBody StationDeleteRequestDTO stationDeleteRequestDTO){
        return this.stationBusiness.deleteById(stationDeleteRequestDTO);
    }

    @PutMapping
    private Result updateById(@Valid @RequestBody StationUpdateRequestDTO stationUpdateRequestDTO){
        return this.stationBusiness.updateById(stationUpdateRequestDTO);
    }

}
