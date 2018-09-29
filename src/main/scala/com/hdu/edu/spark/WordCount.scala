package com.hdu.edu.spark

import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder()
      .master("local")
      .appName("WordCount")
      .getOrCreate()

    val sc = sparkSession.sparkContext

    val file = sc.textFile("./pom.xml")
    val wordCount = file.map(line => (line, 1)).reduceByKey(_ + _)
    wordCount.collect()
  }

}
