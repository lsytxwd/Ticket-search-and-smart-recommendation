package com.watermelon.controller;

import com.alibaba.fastjson.JSON;
import com.watermelon.entity.Flight;
import com.watermelon.entity.Query;
import com.watermelon.service.CSVParseServiceImpl;
import com.watermelon.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "查询航班信息",notes = "分页查询所有航班信息")
    @GetMapping("/searchAll")
    public Object searchAllAirline(@ApiParam(value="页数",example="1") @RequestParam int pageNumber ,
                                   @ApiParam(value="页内数据条数",example="10") @RequestParam int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);
        return searchService.searchAll(pageRequest);
    }

    @ApiOperation(value = "详细查询航班信息",notes = "根据详细信息匹配查询航班信息，约束条件为模糊匹配")
    @PostMapping("/searchByQuery")
    public List searchAirline(@ApiParam(value="页数",example="1") @RequestParam int pageNumber ,
                              @ApiParam(value="页内数据条数",example="10") @RequestParam int pageSize,
                              @ApiParam(value="查询条件") Query query){
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);
        return searchService.searchByQuery(query,pageRequest);
    }

    @ApiOperation(value = "查询城市信息",notes = "分页查询城市信息，返回城市名称及三字码")
    @GetMapping("/searchCity")
    public Page searchCity(@ApiParam(value="页数",example="1") @RequestParam int pageNumber ,
                           @ApiParam(value="页内数据条数",example="10") @RequestParam int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);
        return searchService.getCity(pageRequest);
    }

    @ApiOperation(value = "查询航班信息",notes = "根据Id查询单个航班信息")
    @GetMapping("/searchById")
    public Object searchById(@ApiParam(value="航线id",example="1") @RequestParam long id) {
        Flight flight = searchService.searchById(id);
        Object object = JSON.toJSON(flight);
        return object;
    }

    @Deprecated
    @ApiOperation(value = "查询CSV数据",notes = "从.csv文件中查询数据,size设定查询数量，putDatabase设定是否写入数据库")
    @GetMapping("/searchCSV")
    public Object searchFromCSVFile(@ApiParam(value="数据条数",example="50") @RequestParam int size,
                                    @ApiParam(value="是否将查询的数据写入数据库",example="false") @RequestParam boolean putDatabase) throws IOException, ParseException {
        String url = "D:/部分文件/课程内容/实习/文档/ticket1.csv";
        List<Flight> list = new CSVParseServiceImpl().parseCSV(url,size);
        if (putDatabase){
            for (Flight f : list){
                searchService.saveFlight(f);
            }
        }
        Object object = JSON.toJSON(list);
        return object;
    }
}
