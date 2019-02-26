package com.spark.streaming

import java.lang.System
import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("ChiSquareTest")
      .master("local")
      .getOrCreate()

    var properties = System.getProperties();
    properties.put("user", "hdfs")
    val sc = spark.sparkContext
    val data  = sc.textFile("hdfs://master:8020/apps/hbase/data/hbase.id")
    data.saveAsTextFile("hdfs://master:8020/app/")
    data.collect().foreach(println)
  }

}
