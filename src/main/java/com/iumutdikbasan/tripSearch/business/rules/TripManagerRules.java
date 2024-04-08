package com.iumutdikbasan.tripSearch.business.rules;

import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.common.results.ResultMessage;
import com.iumutdikbasan.tripSearch.repository.StationRepository;
import com.iumutdikbasan.tripSearch.repository.TripRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TripManagerRules {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StationRepository stationRepository;

    public void checkIfTripExists(String id) {
        this.tripRepository.findById(id).orElseThrow(()-> new RuntimeException(ResultMessage.TRIP_COULDNT_FIND.toString()));
    }

    public void checkIfDateTimeIsBeforeProblem(ZonedDateTime zonedDateTime){
        ZonedDateTime now = ZonedDateTime.now();
        if(zonedDateTime.isBefore(now)){
            throw new BusinessException(ResultMessage.DEPARTDATETIME_IS_BEFORE.toString());
        }
    }

    public void checkReturnDateTimeValidity(ZonedDateTime departDate, ZonedDateTime returnDate){
        if(returnDate.isBefore(departDate)){
            throw new BusinessException(ResultMessage.DEPARTDATETIME_IS_BEFORE.toString());
        }
    }

    public void checkPrice(BigDecimal price){
        if (price.doubleValue()<=0){
            throw new BusinessException(ResultMessage.CHECK_PRICE.toString());
        }
    }

    public void checkStationExistence(String departureStationId, String returnStationId){
        if(!this.stationRepository.existsById(departureStationId) || !this.stationRepository.existsById(returnStationId)){
            throw new BusinessException(ResultMessage.STATION_ISNT_AVAILABLE.toString());
        }
        if(!this.stationRepository.findById(departureStationId).orElseThrow().getIsActive() ||
                !this.stationRepository.findById(returnStationId).orElseThrow().getIsActive()){
            throw new BusinessException(ResultMessage.STATION_ISNT_AVAILABLE.toString());
        }
    }
    public void checkIfStationIdString(Object id){
        if(!(id instanceof String)){
            throw new BusinessException(ResultMessage.BAD_INPUT.toString());
        }
    }
    public void checkTripRouteValidity(String departureStationId, String returnStationId){
        if(departureStationId.equals(returnStationId)){
            throw new BusinessException(ResultMessage.STATIONS_ARE_SAME.toString());
        }
    }
}
