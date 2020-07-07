package com.watermelon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询实体
 * 用于构造机票查询的查询条件集合
 * 版本 1.0
 * 日期 2020/7/5
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Query {

    /**
     * isTransit 是否中转
     * departureCityName 出发地
     * arrivalCityName 目的地
     * departureDate 出发时间
     * arrivalDate 抵达时间
     * cabinType 机舱等级(枚举：头等舱、商务舱、经济舱)
     * departureAirportName 出发机场名称
     * arrivalAirportName 目的机场名称
     * airlineName 航空公司
     */
    private boolean isTransit;
    private String departureCityName;
    private String arrivalCityName;
    private String departureDate;
    private String arrivalDate;
    private CabinType cabinType;
    private String departureAirportName;
    private String arrivalAirportName;
    private String airlineName;

}
