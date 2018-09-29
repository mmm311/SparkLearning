package com.hdu.edu.spark.sql


import org.apache.spark.sql.SparkSession


object SparkSqlTest {
  case class Person(name: String, age: Long)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("spark sql")
      .master("local")
      .getOrCreate()

    import spark.implicits._
    val peopleDF = spark.sparkContext
      .textFile("data/resources/people.txt")
      .map(_.split(","))
      .map(attributes => Person(attributes(0), attributes(1).trim.toInt))
      .toDF()

    peopleDF.createOrReplaceTempView("people")
    peopleDF.show()
    val teenagersDF = spark.sql("select name, age from people where age between 13 and 14 ")
    teenagersDF.map(teenager => "Name: " + teenager(0)).show()

    teenagersDF.map(teenager => "Name: " + teenager.getAs[String]("name")).show()

    implicit  val mapEncoder = org.apache.spark.sql.Encoders.kryo[Map[String, Any]]

    teenagersDF.map(teenager => teenager.getValuesMap[Any](List("name", "age"))).collect()
  }
}
