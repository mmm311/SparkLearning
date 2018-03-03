package com.hdu.edu.graphXInAction

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.tuning.{CrossValidator, ParamGridBuilder}
import org.apache.spark.sql.SparkSession

object SparkML {
  case class Review(text: String, label: Double)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()
    import spark.implicits._

    val lines = spark.sparkContext.textFile("E:\\sparkdata\\sentimentlabelledsentences\\imdb_labelled.txt")
    val reviews = lines.map{_.split("\\t")}.map{a => Review(a(0), a(1).toDouble)}.toDF()
    val Array(trainingData, testData) = reviews.randomSplit(Array(0.8, 0.2))
    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    val hashingTF = new HashingTF()
      .setNumFeatures(100)
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")
    val lr = new LogisticRegression()
      .setMaxIter(30)
      .setRegParam(0.01)
    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTF, lr))

    val pipeLineMode = pipeline.fit(trainingData)
    val trainingPredictions = pipeLineMode.transform(trainingData)

    val evaluator = new BinaryClassificationEvaluator()
    val evaluatorParamMap = ParamMap(evaluator.metricName -> "areaUnderROC")
    val aucTraining = evaluator.evaluate(trainingPredictions, evaluatorParamMap)
    println(s"aucTraing: ${aucTraining}")

    val testPredictions = pipeLineMode.transform(testData)
    val aucTest = evaluator.evaluate(testPredictions, evaluatorParamMap)
    println(s"aucTest: ${aucTest}" )
    spark.stop()



  }

}
