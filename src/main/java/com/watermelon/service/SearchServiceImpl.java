package com.watermelon.service;

import com.watermelon.entity.CabinType;
import com.watermelon.entity.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public List searchAll() {
        return null;
    }

    @Override
    public List searchByQuery(Query query) {
        String startPoint = query.getStartPoint();
        String destination = query.getDestination();
        String startTime = query.getStartTime();
        String returnTime = query.getReturnTime();
        CabinType cabinType = query.getCabinType();
        String airport = query.getAirPort();
        String airlineCompany = query.getAirlineCompany();
        return null;
    }

    @Override
    public List getCity() {
        return null;
    }
}
