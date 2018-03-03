package com.hdu.edu.ActorTest

import org.apache.spark.sql.SparkSession

object Test {
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .config("spark.executor.memory", "1024m")
      .getOrCreate()

    val sc = spark.sparkContext

    val conf = sc.getConf
    val executorMemory = conf.getOption("spark.executor.memory")
      .orElse(Option(System.getenv("SPARK_EXECUTOR_MEMORY")))
      .orElse(Option(System.getenv("SPARK_MEM")))

    println(show(executorMemory))


  }
}
