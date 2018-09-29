package com.spark.streaming


import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Milliseconds, Seconds, StreamingContext}
import org.apache.kafka.common.serialization.{DoubleDeserializer, StringDeserializer}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent


object KafkaRandom {

  def main(args: Array[String]) {

    // Create context with 2 second batch interval
    val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount").setMaster("local[*]")
    val ssc = new StreamingContext(sparkConf, Seconds(2))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "master:6667",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "sex"
    )

    val topics = Array("CustomerCountry")
    val messages = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    // Get the lines, split them into words, count the words and print
    val words = messages.map(record => record.value).flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.repartition(1).foreachRDD{ rdd =>
      rdd.foreach(println)
    }
    // Start the computation
    ssc.start()
    ssc.awaitTermination()
  }

}
