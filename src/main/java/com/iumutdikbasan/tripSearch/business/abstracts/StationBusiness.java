package com.iumutdikbasan.tripSearch.business.abstracts;

import com.iumutdikbasan.tripSearch.common.results.DataResult;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.dto.request.station.StationDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.station.StationSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.station.StationUpdateRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.StationsResponseDTO;

import java.util.List;

public interface StationBusiness {
    DataResult<List<StationsResponseDTO>> getAll();
    Result addStation(StationSaveRequestDTO stationSaveRequestDTO);
    Result deleteById(StationDeleteRequestDTO stationDeleteRequestDTO);
    Result updateById(StationUpdateRequestDTO stationUpdateRequestDTO);
}
