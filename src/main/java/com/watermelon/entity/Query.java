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
     * peopleNumber 人数
     * startPoint 出发地
     * destination 目的地
     * startTime 出发时间
     * returnTime 返程时间
     * cabinType 机舱等级(枚举：头等舱、商务舱、经济舱)
     * airPort 机场
     * airlineCompany 航空公司
     */
    private int peopleNumber;
    private String startPoint;
    private String destination;
    private String startTime;
    private String returnTime;
    private CabinType cabinType;
    private String airPort;
    private String airlineCompany;

}
