package com.watermelon.controller;

import com.watermelon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    @ResponseBody
    public List<Map<String,Object>> test(){
        return testService.getProvinceData();
    }

}
