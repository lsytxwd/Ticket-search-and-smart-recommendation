package com.watermelon.repository;

import com.watermelon.entity.Flight;
import com.watermelon.entity.QueryResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findAll(Pageable pageable);

    @Query("select f from Flight f where " +
            "f.departureCityName like ?1 or " +
            "f.arrivalCityName like ?2 or " +
            "DATE_FORMAT(f.departureDate,'%Y-%m-%d') like ?3 or " +
            "DATE_FORMAT(f.arrivalDate,'%Y-%m-%d') like ?4 or " +
            "f.departureAirportName like ?5 or " +
            "f.airlineName like ?6")
    List<Flight> findByQuery(String departureCityName,
                             String arrivalCityName,
                             String departureDate,
                             String arrivalDate,
                             String departureAirportName,
                             String airlineName,
                             PageRequest request);

    @Query("select min(price) from Flight where departureDate=str_to_date(?1,'%Y-%m-%d')")
    Integer findMinPriceByDepartureDate(String date);

    @Query("select avg(price) from Flight where month(departureDate)=?1")
    Integer findPriceByMonth(Integer month);

    @Query(" select new com.watermelon.entity.QueryResult(arrivalCityName,min(price)) from Flight where departureCityName=?1 group by arrivalCityName")
    List<QueryResult> findCityAndPrice(String city);

}
