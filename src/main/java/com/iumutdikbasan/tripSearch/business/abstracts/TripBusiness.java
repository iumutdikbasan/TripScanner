package com.iumutdikbasan.tripSearch.business.abstracts;

import com.iumutdikbasan.tripSearch.common.results.DataResult;
import com.iumutdikbasan.tripSearch.common.results.Result;
import com.iumutdikbasan.tripSearch.dto.request.search.SearchRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripUpdateRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.TripsResponseDTO;

import java.util.List;

public interface TripBusiness {
    DataResult<List<TripsResponseDTO>> getAll();
    Result addTrip(TripSaveRequestDTO tripSaveRequestDTO);
    Result deleteTrip(TripDeleteRequestDTO tripDeleteRequestDTO);
    Result updateTrip(TripUpdateRequestDTO tripUpdateRequestDTO);
    Result searchTrips(SearchRequestDTO searchRequestDTO);
    //todo
}
