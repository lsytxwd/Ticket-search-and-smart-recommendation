package com.watermelon.service;

import com.watermelon.entity.Flight;
import com.watermelon.entity.Query;
import com.watermelon.repository.CityRepository;
import com.watermelon.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Page<Flight> searchAll(PageRequest pageRequest) {
        return flightRepository.findAll(pageRequest);
    }

    @Override
    public List searchByQuery(Query query,PageRequest request) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String departureCityName = query.getDepartureCityName();
        String arrivalCityName = query.getArrivalCityName();
        Date departureDate = format.parse(query.getDepartureDate());
        Date arrivalDate = format.parse(query.getArrivalDate());
        String departureAirportName = query.getDepartureAirportName();
        String airlineName = query.getAirlineName();
        return flightRepository.findByQuery(departureCityName,arrivalCityName,departureDate,arrivalDate,departureAirportName,airlineName,request);
    }

    @Override
    public Flight searchById(Long id) {
        return flightRepository.getOne(id);
    }


    @Override
    public Page getCity(PageRequest pageRequest) {
        return cityRepository.findAll(pageRequest);
    }

    @Override
    public void saveFlight(Flight flight) {

        flightRepository.save(flight);
    }

    @Override
    public void saveAll(List<Flight> list) {
        flightRepository.saveAll(list);
    }


}
