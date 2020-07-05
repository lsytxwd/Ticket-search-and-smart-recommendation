package com.watermelon.controller;

import com.watermelon.entity.Query;
import com.watermelon.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/searchAll")
    public List searchAllAirline(){
        return searchService.searchAll();
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
