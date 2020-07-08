package com.watermelon.controller;

import com.alibaba.fastjson.JSON;
import com.watermelon.entity.Flight;
import com.watermelon.entity.Query;
import com.watermelon.service.CSVParseServiceImpl;
import com.watermelon.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Value("${csv-path}")
    private String csvPath;

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
                              @ApiParam(value="查询条件") Query query) throws Exception {
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

    @ApiOperation(value = "查询未来一段时间内单日最低票价",notes = "根据起始日期和截止日期查询这一段时间内的最低票价 ")
    @GetMapping("/searchMinPriceByMonth")
    public Map getMinPriceByMonth(@ApiParam(value="起始日期",example="2020-07-06") @RequestParam String begin,
                                  @ApiParam(value="截止日期",example="2020-08-06")@RequestParam String end) throws Exception {
        Map<String,Object> map = searchService.getMinPriceByMonth(begin,end);
        return map;
    }

    @ApiOperation(value = "查询可达目的地的最低价",notes = "根据出发地查找可达的目的地及其对应的最低票价")
    @GetMapping("/searchCityPrice")
    public Map getCityAndPrice(@ApiParam(value="出发城市",example="成都") @RequestParam String city) throws Exception {
        Map<String,Integer> map = searchService.getCityAndPrice(city);
        return map;
    }

    @Deprecated
    @ApiOperation(value = "查询CSV数据(不推荐使用)",notes = "从.csv文件中查询数据,size设定查询数量，putDatabase设定是否写入数据库")
    @GetMapping("/searchCSV")
    public Object searchFromCSVFile(@ApiParam(value="数据条数",example="50") @RequestParam int size,
                                    @ApiParam(value="是否将查询的数据写入数据库",example="false") @RequestParam boolean putDatabase) throws IOException, ParseException {
        String url = csvPath;
        List<Flight> list = new CSVParseServiceImpl().parseCSV(url,size);
        if (putDatabase){
            searchService.saveAll(list);
        }
        Object object = JSON.toJSON(list);
        return object;
    }
}
