package com.watermelon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.watermelon.entity.Flight;
import com.watermelon.entity.Query;
import com.watermelon.repository.FlightRepository;
import com.watermelon.service.CSVParseServiceImpl;
import com.watermelon.service.SearchService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/searchAll")
    public Object searchAllAirline(@RequestParam int size) throws IOException, ParseException {
        String url = "D:/部分文件/课程内容/实习/文档/ticket1.csv";
        List<Flight> list = new CSVParseServiceImpl().parseCSV(url,size);
        Object object = JSON.toJSON(list);
        return object;
//        return searchService.searchAll();
    }

    @PostMapping("/searchByQuery")
    public List searchAirline( @ApiParam(value="查询条件")@RequestParam(value="startPage",required=false) Query query){
        return searchService.searchByQuery(query);
    }

    @PostMapping("/getCity")
    public List getCity(){
        return searchService.getCity();
    }

    @GetMapping("/getOne")
    public Object getOne() {
        Flight flight = flightRepository.getOne((long) 1);
        List<Flight> list = new ArrayList<>();
        list.add(flight);
        Object object = JSON.toJSON(list);
        return object;
    }
}
