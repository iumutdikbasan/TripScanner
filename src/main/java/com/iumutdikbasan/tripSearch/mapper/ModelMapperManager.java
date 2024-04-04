package com.iumutdikbasan.tripSearch.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperBusiness {

    private final ModelMapper modelMapper;

    public ModelMapperManager(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
                /*
                choose one when ambiguity
                */
        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
                /*
                Ignore columns in the DTO that do not have corresponding attributes
                 in the database without raising an error.
                */
        return this.modelMapper;
    }
}