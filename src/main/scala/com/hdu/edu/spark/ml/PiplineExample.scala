package com.hdu.edu.spark.ml

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.ml.linalg.Vector

object PiplineExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()

    val training = spark.createDataFrame(Seq(
      (0L, "a b c d e spark", 1.0),
      (1L, "b d", 0.0),
      (2L, "spark f g h", 1.0),
      (3L, "hadoop mapreduce", 0.0)
    )).toDF("id", "text", "label")

    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")
    val hashingTF = new HashingTF()
      .setNumFeatures(1000)
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")
    val lr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.001)

    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTF, lr))

    val model = pipeline.fit(training)

    model.write.overwrite().save("/data/spark-logistic-regression-model")

    pipeline.write.overwrite().save("data/unfit-lr-model")

    val sameModel = PipelineModel.load("/data/spark-logistic-regression-model")
    val text = spark.createDataFrame(Seq(
      (4L, "spark i j k"),
      (5L, "1 m n"),
      (6L, "spark hadoop spark"),
      (7L, "apache hadoop")
      
    )).toDF("id", "text")

    model.transform(text)
      .select("id", "text", "probability", "prediction")
      .collect()
      .foreach{case Row(id: Long, text: String, prob: Vector, prediction: Double)=>
        println(s"{$id, $text) --> prob=$prob, prediction=$prediction")
      }
    spark.stop()
  }

}
