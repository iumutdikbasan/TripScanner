package com.iumutdikbasan.tripSearch.repository;

import com.iumutdikbasan.tripSearch.model.concretes.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,String> {
    boolean existsByDepartStationName(String departStationName);
    boolean existsByReturnStationName(String returnStationName);
    boolean existsById(String id);

    List<Trip> findAllByReturnDateIsNotNull();
    List<Trip> findAllByDepartStationName(String departStationName);
}
