package com.hdu.edu.spark.iteblog

import org.apache.spark.sql.SparkSession

object Partitioning {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession
      .builder()
      .appName("partitioning")
      .master("local")
      .getOrCreate()

    val sc = sparkSession.sparkContext
    val n = 2000000
    val composite = sc.parallelize(2 to n, 4).map(x => (x, (2 to (n / x)))).repartition(4).flatMap(kv => kv._2.map(_ * kv._1))
    val prime = sc.parallelize(2 to n, 4).subtract(composite)
    prime.collect().foreach(println)

  }
}
