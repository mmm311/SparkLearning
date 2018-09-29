package com.hdu.edu.spark.mlib

import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.{Row, SparkSession}

object CorrelationExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("CorrelationExample")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val data = Seq(
      Vectors.dense(1.0, 0, 0,-2,0),
      Vectors.dense(4.0, 5.0, 0.0, 3.0, 0),
      Vectors.dense(6.0, 7.0, 0.0, 8.0, 0),
      Vectors.dense(9, 0, 0, 1, 0),
      Vectors.dense(9, 0, 0, 0.9, 0)
    )


    val df = data.map(Tuple1.apply).toDF("features")
    df.show()
    val Row(coeff1: Matrix) = Correlation.corr(df, "features").head()
    println("Pearson correlation matrix: \n" + coeff1.toString())

    val Row(coeff2 : Matrix) = Correlation.corr(df, "features", "spearman").head()
    println("Spearman correlation matrix: \n" + coeff2.toString())

    spark.stop()
  }
}
