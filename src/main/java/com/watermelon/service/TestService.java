package com.watermelon.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface TestService {

    List<Map<String,Object>> getProvinceData();

}
