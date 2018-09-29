package com.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("word_count")
      .setMaster("local")

    val ssc = new StreamingContext(conf, Seconds(1))
  }

}
