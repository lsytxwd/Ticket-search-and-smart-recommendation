package com.watermelon.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSql {
    public Dataset<Row> Query(String sqltext) {
        SparkConf conf = new SparkConf().setMaster("yarn").setAppName("SqarkSql");
        SparkContext ctx = new SparkContext(conf);
        SparkSession sc = new SparkSession(ctx);
        Dataset<Object> df = null;
        Dataset<Row> sql = sc.sql(sqltext);
        return sql;
    }
}
