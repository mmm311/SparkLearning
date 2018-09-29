package com.spark.mlib

import org.apache.spark.sql.SparkSession

object Word2VecExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Word2Vec")
      .master("local")
      .getOrCreate()

  }
}
