package com.spark.mlib

import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.{Row, SparkSession}

object CorrelationExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("Correlation")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val data = Seq(

      Vectors.dense(12.5, 15.3, 23.2),
      Vectors.dense(21.2, 23.9, 32.9)

    )

    val df = data.map(Tuple1.apply).toDF("features")
    df.show()
    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head()
    println("Pearson correlation matrix:\n" + coeff1.toString())

    val Row(coeff2: Matrix) = Correlation.corr(df, "features","spearman").head()
    println("spearman correlation matrix:\n" + coeff2.toString())
    spark.stop()

  }

}
