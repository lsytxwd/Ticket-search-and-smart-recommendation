package com.watermelon.service;

import com.watermelon.entity.Flight;
import com.watermelon.entity.Query;
import com.watermelon.entity.QueryResult;
import com.watermelon.repository.CityRepository;
import com.watermelon.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String departureCityName = query.getDepartureCityName();
        String arrivalCityName = query.getArrivalCityName();
        String departureDate = query.getDepartureDate();
        String arrivalDate = query.getArrivalDate();
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

    @Override
    public Map getMinPriceByMonth(String begin,String end) throws ParseException {
        Map<Date,Integer> dateMap = new HashMap<>();
        Map<Date,Integer> monthMap = new HashMap<>();
        List<String> dates = listDate(begin,end,Calendar.DATE);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //根据日期查询最低价
        for (String dateStr : dates){
            Integer minPrice = flightRepository.findMinPriceByDepartureDate(dateStr);
            Date d = format.parse(dateStr);
            dateMap.put(d,minPrice);
        }
        //根据月份查询每个月的平均价格
        List<String> months = listDate(begin,end,Calendar.MONTH);
        for (String dateStr : months){
            Date date = format.parse(dateStr);
            Integer monthNumber = date.getMonth();
            Integer minPrice = flightRepository.findPriceByMonth(monthNumber);
            monthMap.put(date,minPrice);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("month",monthMap);
        map.put("date",dateMap);
        return map;
    }

    public Map getCityAndPrice(String city){
        Map<String,Integer> map = new HashMap<>();
        List<QueryResult> result = flightRepository.findCityAndPrice(city);
        for (QueryResult qr : result){
            map.put(qr.getArrivalCityName(),qr.getMinPrice());
        }
        return map;
    }

    /**
     * 根据输入的起始时间、终止时间、时间单位生成一段时间内包含的时间点对应的日期
     * @param begin
     * @param end
     * @param DATE
     * @return list
     * @throws ParseException
     */
    private List<String> listDate(String begin, String end,int DATE) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = format.parse(begin);
        Date endDate = format.parse(end);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        List<String> list = new ArrayList<>();
        //遍历一段时间内所包含的时间点
        while (!calendar.getTime().after(endDate)){
            list.add(format.format(calendar.getTime()));
            calendar.add(DATE,1);
        }
        return list;
    }

}
