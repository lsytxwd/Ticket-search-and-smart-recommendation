package com.watermelon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getProvinceData() {
        return jdbcTemplate.queryForList("select * from province_purchase_power order by province");
    }

}
