package com.watermelon.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;

public class SparkExample {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkExample");

        JavaSparkContext context = new JavaSparkContext(conf);
        JavaRDD<Integer> rdd = context.parallelize(Arrays.asList(1,2,3,4,5));
        JavaRDD<Integer> result = rdd.map(x -> x*2);
        result.foreach(x -> System.out.println(x));
        context.close();
    }

}
