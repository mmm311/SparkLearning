package com.hdu.edu.spark.sql

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

object MergeSchemaTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()

    import spark.implicits._
    val squaresDF = spark.sparkContext.makeRDD(1 to 5).map(i => (i, i * i))
      .toDF("value", "square")
    squaresDF.write.parquet("data/test_table/key=1")

    val cubesDF = spark.sparkContext.makeRDD(6 to 10).map(i => (i, i * i * i))
      .toDF("value", "cube")
    cubesDF.write.parquet("data/test_table/key=2")

    val mergedDF = spark.read.option("mergeSchema", "true").parquet("data/test_table")
    mergedDF.printSchema()
    mergedDF.show()
    spark.stop()
  }
}
