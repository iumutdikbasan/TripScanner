package com.iumutdikbasan.tripSearch.business.concretes;

import com.iumutdikbasan.tripSearch.business.abstracts.TripBusiness;
import com.iumutdikbasan.tripSearch.business.rules.SearchRules;
import com.iumutdikbasan.tripSearch.business.rules.TripManagerRules;
import com.iumutdikbasan.tripSearch.common.exceptions.BusinessException;
import com.iumutdikbasan.tripSearch.common.results.*;
import com.iumutdikbasan.tripSearch.dto.request.search.SearchRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripDeleteRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripSaveRequestDTO;
import com.iumutdikbasan.tripSearch.dto.request.trip.TripUpdateRequestDTO;
import com.iumutdikbasan.tripSearch.dto.response.SearchResponseDTO;
import com.iumutdikbasan.tripSearch.dto.response.TripsResponseDTO;
import com.iumutdikbasan.tripSearch.mapper.ModelMapperBusiness;
import com.iumutdikbasan.tripSearch.model.concretes.Trip;
import com.iumutdikbasan.tripSearch.repository.TripRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class TripManager implements TripBusiness {

    //todo : logger , mockapi

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripManagerRules tripManagerRules;
    @Autowired
    private ModelMapperBusiness modelMapperBusiness;
    @Autowired
    private SearchRules searchRules;

    @Override
    public DataResult<List<TripsResponseDTO>> getAll() {
        List<Trip> trips = this.tripRepository.findAll();
        List<TripsResponseDTO> getAllTripsResponses = new ArrayList<>();
        for (Trip t : trips) {
            getAllTripsResponses.add(this.modelMapperBusiness.forResponse().map(t, TripsResponseDTO.class));
        }
        return new SuccessDataResult<>(getAllTripsResponses);
    }

    @Override
    public Result addTrip(TripSaveRequestDTO tripSaveRequestDTO) {
        this.tripManagerRules.checkIfDateTimeIsBeforeProblem(tripSaveRequestDTO.getDepartDate());
        if(tripSaveRequestDTO.getReturnDate() != null){
            this.tripManagerRules.checkReturnDateTimeValidity(tripSaveRequestDTO.getDepartDate(), tripSaveRequestDTO.getReturnDate());
        }
        // Rules
        this.tripManagerRules.checkTripRouteValidity(tripSaveRequestDTO.getDepartStationId(),tripSaveRequestDTO.getReturnStationId());
        this.tripManagerRules.checkPrice(tripSaveRequestDTO.getPrice());
        this.tripManagerRules.checkStationExistence(tripSaveRequestDTO.getDepartStationId(),tripSaveRequestDTO.getReturnStationId());
        this.tripManagerRules.checkIfStationIdString(tripSaveRequestDTO.getDepartStationId());
        this.tripManagerRules.checkIfStationIdString(tripSaveRequestDTO.getDepartStationId());
        this.tripManagerRules.checkIfStationIdString(tripSaveRequestDTO.getReturnStationId());
        //Mapping
        Trip trip = modelMapperBusiness.forRequest().map(tripSaveRequestDTO, Trip.class);
        trip.setId(UUID.randomUUID().toString());
        this.tripRepository.save(trip);
        return new SuccessResult(ResultMessage.CREATED.toString());
    }

    @Override
    public Result deleteTrip(TripDeleteRequestDTO tripDeleteRequestDTO) {
        this.tripManagerRules.checkIfTripExists(tripDeleteRequestDTO.getId());
        this.tripRepository.deleteById(tripDeleteRequestDTO.getId());
        return new SuccessResult(ResultMessage.SUCCESS.toString());
    }

    @Override
    public Result updateTrip(TripUpdateRequestDTO tripUpdateRequestDTO) {
        this.tripManagerRules.checkIfTripExists(tripUpdateRequestDTO.getId());
        this.tripManagerRules.checkIfDateTimeIsBeforeProblem(tripUpdateRequestDTO.getDepartDate());
        if(tripUpdateRequestDTO.getReturnDate() != null){
            this.tripManagerRules.checkReturnDateTimeValidity(tripUpdateRequestDTO.getDepartDate(), tripUpdateRequestDTO.getReturnDate());
        }
        Trip trip = this.modelMapperBusiness.forRequest().map(tripUpdateRequestDTO, Trip.class);
        this.tripRepository.save(trip);
        return new SuccessDataResult<>(ResultMessage.SUCCESS.toString());
    }

    @Override
    public Result searchTrips(SearchRequestDTO searchRequestDTO) {
        searchRequestDTO.setReturnStationName(searchRequestDTO.getReturnStationName().toUpperCase());
        searchRequestDTO.setDepartStationName(searchRequestDTO.getDepartStationName().toUpperCase());
        List<SearchResponseDTO> searchResponseDTOList;
        List<SearchResponseDTO> alternativeTrips= new ArrayList<>();
        if(searchRequestDTO.getReturnDate() == null){
            System.out.println("One way trip.");
            List<Trip> trips = this.tripRepository.findAll();
            // One-way rules:
            searchResponseDTOList = trips.stream().filter((trip) ->
                    this.searchRules.checkIfCityMatch(trip.getDepartStation().getCity(),searchRequestDTO.getDepartStationName()) &&
                    this.searchRules.checkIfCityMatch(trip.getReturnStation().getCity(),searchRequestDTO.getReturnStationName()) &&
                    this.searchRules.checkIfDepartDateTimeDecent(trip.getDepartDate(),searchRequestDTO.getDepartDate())
            ).map(filteredTrip -> this.modelMapperBusiness.forResponse().map(filteredTrip,SearchResponseDTO.class)).collect(Collectors.toList());
            alternativeTrips = trips.stream().filter((trip) ->
                    this.searchRules.checkIfCityMatch(trip.getReturnStation().getCity(),searchRequestDTO.getDepartStationName()) &&
                            this.searchRules.checkIfCityMatch(trip.getDepartStation().getCity(),searchRequestDTO.getReturnStationName()) &&
                            this.searchRules.checkIfDepartDateTimeDecent(trip.getReturnDate(),searchRequestDTO.getDepartDate())
            ).map(filteredTrip ->{
                SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
                searchResponseDTO.setPrice(filteredTrip.getPrice().divide(new BigDecimal("2")));
                searchResponseDTO.setDepartDate(filteredTrip.getReturnDate());
                searchResponseDTO.setReturnStation(filteredTrip.getDepartStation().getCity());
                searchResponseDTO.setDepartStation(filteredTrip.getReturnStation().getCity());
                return searchResponseDTO;
            }).toList();
            searchResponseDTOList.addAll(alternativeTrips);
        }else{
            //Two-way rules:
            System.out.println("Two way trip.");
            List<Trip> trips = this.tripRepository.findAllByReturnDateIsNotNull();
            searchResponseDTOList = trips.stream().filter((trip)->
                    this.searchRules.checkIfCityMatch(trip.getDepartStation().getCity(),searchRequestDTO.getDepartStationName()) &&
                    this.searchRules.checkIfCityMatch(trip.getReturnStation().getCity(),searchRequestDTO.getReturnStationName()) &&
                            this.searchRules.checkIfDepartDateTimeDecent(trip.getDepartDate(),searchRequestDTO.getDepartDate()) &&
                            this.searchRules.checkIfReturnDateTimeDecent(trip.getReturnDate(),searchRequestDTO.getReturnDate())
            ).map(filteredTrip -> this.modelMapperBusiness.forResponse().map(filteredTrip,SearchResponseDTO.class)).collect(Collectors.toList());
        }
        if(searchResponseDTOList.isEmpty()){
            throw new BusinessException(ResultMessage.SEARCH_COULDNT_FIND.toString());
        }
        return new SuccessDataResult<>(ResultMessage.SUCCESS.toString());
    }
    //todo :fetch
}
