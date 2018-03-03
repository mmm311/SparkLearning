package com.hdu.edu.spark.ml

import org.apache.spark.ml.feature.ChiSqSelector
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

object ChiSqSelectorExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val data = Seq(
      (7, Vectors.dense(0.0, 0.0, 18.0, 1.0), 1.0),
      (8, Vectors.dense(0.0, 1.0, 12.0, 0.0), 0.0),
      (9, Vectors.dense(1.0, 0.0, 15.0, 0.1), 0.0)
    )
    val df = spark.createDataset(data).toDF("id", "features", "clicked")
    df.show()

    val selector = new ChiSqSelector()
      .setNumTopFeatures(1)
      .setFeaturesCol("features")
      .setLabelCol("clicked")
      .setOutputCol("selectedFeatures")


    val result = selector.fit(df).transform(df)

    println(s"ChiSqSelector output with top ${selector.getNumTopFeatures} features selected")
    result.show()
    spark.stop()
  }

}
