package com.iumutdikbasan.tripSearch.business.rules;

import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.common.results.ResultMessage;
import com.iumutdikbasan.tripSearch.repository.StationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class StationManagerRules {
    @Autowired
    StationRepository stationRepository;

    public void checkIfNameExists(String name){
        if(this.stationRepository.existsByName(name)){
            throw new BusinessException(ResultMessage.TRIP_ALREADY_ADDED.toString());
        }
    }
}
