package com.watermelon.controller;

import com.watermelon.entity.Query;
import com.watermelon.service.CSVParseServiceImpl;
import com.watermelon.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/searchAll")
    public List searchAllAirline() throws IOException, ParseException {
        String url = "D:/部分文件/课程内容/实习/文档/ticket1.csv";
        return new CSVParseServiceImpl().parseCSV(url);
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
}
