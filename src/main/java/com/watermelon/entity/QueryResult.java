package com.watermelon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于存放查询的可达城市和最低票价数据
 * 版本 1.0
 * 日期 2020/7/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult {

    private String arrivalCityName;
    private Integer minPrice;

}
