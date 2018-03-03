package com.hdu.edu.spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object SqlTest {
  case class Person(name: String, age: Long)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()
    import spark.implicits._
    val dir = "E:\\sparkdata\\resources\\"
    val peopleDF = spark.read.format("json").load(dir + "people.json")
    peopleDF.select("name", "age").write.format("parquet").save("namesAndAges.parquet")
    spark.stop()

  }

}
