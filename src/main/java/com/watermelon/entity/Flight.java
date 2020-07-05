package com.watermelon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 航班信息
 * 版本 1.0
 * 时间 2020/7/5
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    /**
     * airlineName 航空公司名称
     * flightNumber 航班号
     * craftTypeName 飞机机型
     * departureDate 起飞时间
     * arrivalDate 抵达时间
     * departureCityTlc 出发城市三字码
     * departureCityName 出发城市
     * departureAirportTlc 出发地机场三字码
     * departureAirportName 出发地机场名字
     * departureAirportTerminal 出发机场航站楼
     * arrivalCityTlc 目的城市三字码
     * arrivalCityName 目的城市
     * arrivalAirportTlc 目的机场三字码
     * arrivalAirportName 目的机场名
     * arrivalAirportTerminal 目的机场航站楼
     * price 机票价格
     *
     */

    private String airlineName;
    private String flightNumber;
    private String craftTypeName;
    private String departureDate;
    private String arrivalDate;
    private String departureCityTlc;
    private String departureCityName;
    private String departureAirportTlc;
    private String departureAirportName;
    private String departureAirportTerminal;
    private String arrivalCityTlc;
    private String arrivalCityName;
    private String arrivalAirportTlc;
    private String arrivalAirportName;
    private String arrivalAirportTerminal;
    private String price;

}
