package com.spark.mlib

import org.apache.spark.sql.SparkSession

object PipelineExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Pipeline")
      .master("local")
  }

}
