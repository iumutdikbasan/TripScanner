package com.iumutdikbasan.tripSearch.common.results;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ResultMessage {
    SUCCESS("Request Successful."),
    CREATED("Successfully created"),
    CHECK_PRICE("Price can't be free or below."),
    STATIONS_ARE_SAME("Cannot trip to the same station."),
    RETURNDATETIME_IS_BEFORE("Return date cannot be before departure date"),
    DEPARTDATETIME_IS_BEFORE("Cannot book appointments in the past"),
    BAD_INPUT("Check the types of values you entered."),
    BAD_JSON_BODY("JSON syntax error, check the body."),
    REQUESTBODY_CHECK("Request is invalid"),
    STATION_ALREADY_ADDED("Station already exists"),
    STATION_HAS_BEEN_DELETED("Station has been deleted successfully."),
    STATION_ISNT_AVAILABLE("Station doesn't exist."),
    SEARCH_COULDNT_FIND("No trip found matching your criteria."),
    TRIP_COULDNT_FIND("No such trip exists"),
    TRIP_ALREADY_ADDED("Trip already exists");
    private String message;

    public String toString(){
        return this.message;
    }
}
