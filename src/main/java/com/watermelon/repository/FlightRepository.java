package com.watermelon.repository;

import com.watermelon.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findAll(Pageable pageable);

    @Query("select f from Flight f where " +
            "f.departureCityName like ?1 or " +
            "f.arrivalCityName like ?2 or " +
            "f.departureDate = ?3 or " +
            "f.arrivalDate = ?4 or " +
            "f.departureAirportName like ?5 or " +
            "f.airlineName like ?6")
    List<Flight> findByQuery(String departureCityName,
                             String arrivalCityName,
                             String departureDate,
                             String arrivalDate,
                             String departureAirportName,
                             String airlineName,
                             PageRequest request);

}
