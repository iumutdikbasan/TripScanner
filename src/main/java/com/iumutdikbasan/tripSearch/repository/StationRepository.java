package com.iumutdikbasan.tripSearch.repository;

import com.iumutdikbasan.tripSearch.model.concretes.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station,String > {
    boolean existsById(String id);
    boolean existsByName(String name);
    List<Station> findAllByIsActive(boolean isActive);
}
