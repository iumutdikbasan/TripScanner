package com.iumutdikbasan.tripSearch.business.rules;
import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.repository.TripRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SearchRules {
    @Autowired
    private TripRepository tripRepository;

    public Boolean checkIfCityMatch(String stationCity, String searchStationCity) {
        return stationCity.equals(searchStationCity);
    }
    public Boolean checkIfDepartDateTimeDecent(ZonedDateTime departDate, ZonedDateTime searchDateTime){
        if(searchDateTime.isBefore(ZonedDateTime.now())){
            throw new BusinessException("You can't search in the past. Please try searching for dates on or after today.");
        }
        return Math.abs(Duration.between(searchDateTime, departDate).toHours()) <=24;
    }
    public Boolean checkIfReturnDateTimeDecent(ZonedDateTime returnDate, ZonedDateTime searchDateTime){
        if(searchDateTime.isBefore(ZonedDateTime.now())){
            throw new BusinessException("You can't search in the past. Please try searching for dates on or after today.");
        }
        return Math.abs(Duration.between(searchDateTime, returnDate).toHours()) <=24;
    }
}
