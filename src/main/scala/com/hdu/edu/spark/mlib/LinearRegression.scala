package com.hdu.edu.spark.mlib

import org.apache.spark.mllib.optimization.SquaredL2Updater
import org.apache.spark.mllib.regression.LinearRegressionWithSGD
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.sql.SparkSession

object LinearRegression {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()

    val sc = spark.sparkContext

    val filename = "E:\\sparkdata\\data\\mllib\\sample_linear_regression_data.txt"
    val example = MLUtils.loadLibSVMFile(sc, filename)
    val splits = example.randomSplit(Array(0.8, 0.2))
    val training = splits(0).cache()
    val test = splits(1).cache()

    val numTraining = training.count()
    val numTest = test.count()
    println(s"Training: $numTraining, test: $numTest.")

    example.unpersist(blocking = false)
    val updater = new SquaredL2Updater()

    val algorithm = new LinearRegressionWithSGD()
    algorithm.optimizer
      .setNumIterations(100)
      .setStepSize(1)
      .setUpdater(updater)
      .setRegParam(0.1)

    val model = algorithm.run(training)
    val prediction = model.predict(test.map(_.features))
    val predictionAndLabel = prediction.zip(test.map(_.label))

    val loss = predictionAndLabel.map{case (p, l) =>
      val err = p - 1
      err * err
    }.reduce(_ + _)

    val rmse = math.sqrt(loss / numTest)
    println(s"Test RMSE = $rmse")
    spark.stop()
  }


}
