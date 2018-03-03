package com.hdu.edu.spark

import org.apache.spark.sql.SparkSession

class SparkGenerator {
  val spark = SparkSession
    .builder()
    .appName(s"${this.getClass.getSimpleName}")
    .master("local")

}
