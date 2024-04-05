package com.iumutdikbasan.tripSearch.business.concretes;

import com.iumutdikbasan.tripSearch.business.abstracts.StationBusiness;
import com.iumutdikbasan.tripSearch.business.rules.StationManagerRules;
import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.common.results.*;
import com.iumutdikbasan.tripSearch.dto.request.station.StationDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.station.StationSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.station.StationUpdateRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.StationsResponseDTO;
import com.iumutdikbasan.tripSearch.mapper.ModelMapperBusiness;
import com.iumutdikbasan.tripSearch.model.concretes.Station;
import com.iumutdikbasan.tripSearch.repository.StationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class StationManager implements StationBusiness {
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private ModelMapperBusiness modelMapperBusiness;
    @Autowired
    private StationManagerRules stationManagerRules;

    @Override
    public DataResult<List<StationsResponseDTO>> getAll() {
        List<Station> stations = this.stationRepository.findAllByIsActive(true);
        List<StationsResponseDTO> stationsResponseDTOList = new ArrayList<>();
        for(Station station : stations){
            stationsResponseDTOList.add(this.modelMapperBusiness.forResponse().map(station,StationsResponseDTO.class));
        }
        return new SuccessDataResult<List<StationsResponseDTO>>(stationsResponseDTOList);
    }

    @Override
    public Result addStation(StationSaveRequestDTO stationSaveRequestDTO) {
        stationSaveRequestDTO.setName(stationSaveRequestDTO.getName().toUpperCase());
        this.stationManagerRules.checkIfNameExists(stationSaveRequestDTO.getName());
        Station station =this.modelMapperBusiness.forRequest().map(stationSaveRequestDTO, Station.class);
        station.setId(UUID.randomUUID().toString());
        station.setIsActive(true);
        this.stationRepository.save(station);
        return new SuccessResult(ResultMessage.CREATED.toString());
    }

    @Override
    public Result deleteById(StationDeleteRequestDTO stationDeleteRequestDTO) {
        Station station = this.stationRepository.findById(stationDeleteRequestDTO.getId())
                .orElseThrow(()-> new BusinessException("There is no such station."));
        station.setIsActive(false);
        this.stationRepository.save(station);
        return new SuccessResult(ResultMessage.STATION_HAS_BEEN_DELETED.toString());
    }

    @Override
    public Result updateById(StationUpdateRequestDTO stationUpdateRequestDTO) {
        Station station = this.stationRepository.findById(stationUpdateRequestDTO.getId())
                .orElseThrow(()-> new BusinessException("There is no such station."));
        station.setIsActive(stationUpdateRequestDTO.getIsActive());
        this.stationRepository.save(station);
        return new SuccessResult(ResultMessage.SUCCESS.toString());
    }
}
