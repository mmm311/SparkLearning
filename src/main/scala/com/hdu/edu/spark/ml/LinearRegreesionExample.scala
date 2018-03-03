package com.hdu.edu.spark.ml

import org.apache.spark.ml.Transformer
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.mllib.evaluation.RegressionMetrics
import org.apache.spark.sql.{DataFrame, SparkSession}

object LinearRegreesionExample {
  private[ml] def evaluateRegressionModel(
                                           model: Transformer,
                                           data: DataFrame,
                                           labelColName: String): Unit = {
    val fullPredictions = model.transform(data).cache()
    val predictions = fullPredictions.select("prediction").rdd.map(_.getDouble(0))
    val labels = fullPredictions.select(labelColName).rdd.map(_.getDouble(0))
    val RMSE = new RegressionMetrics(predictions.zip(labels)).rootMeanSquaredError
    println(s"  Root mean squared error (RMSE): $RMSE")
  }

  def main(args: Array[String]): Unit = {
    val dataFormat = "libsvm"
    val regParam = 0.0
    val elasticNetParam = 0.0
    val maxIter = 100
    val tol = 1e-6
    val fracTest = 0.2

    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()

    println("LinearRegressionExample with parameters:\n")
    val filename = "E:\\sparkdata\\data\\mllib\\sample_linear_regression_data.txt"
    val origExample: DataFrame= spark.read.format(dataFormat).load(filename)
    val dataFrames: Array[DataFrame] = origExample.randomSplit(Array(1 - fracTest, fracTest), seed = 12345)
    val training = dataFrames(0).cache()
    val test = dataFrames(1).cache()
    val numTraining = training.count()
    val numTest = test.count()
    import org.apache.spark.ml.linalg.Vector
    val numFeatures = training.select("features").first().getAs[Vector](0).size
    println("Loaded data:")
    println(s" numTraining = $numTraining, numTest = $numTest")
    println(s" numFeatures = $numFeatures")
    val lir = new LinearRegression()
      .setFeaturesCol("features")
      .setLabelCol("label")
      .setRegParam(regParam)
      .setElasticNetParam(elasticNetParam)
      .setMaxIter(maxIter)
      .setTol(tol)

    val startTime = System.nanoTime()
    val lirModel = lir.fit(training)
    val elapsedTime = (System.nanoTime() - startTime) / 1e9
    println(s"Training time: $elapsedTime seconds")

    println(s"Weights: ${lirModel.coefficients} Intercept: ${lirModel.intercept}")
    println("Training data results:")
    evaluateRegressionModel(lirModel, training, "label")
    println("Test data results: ")
    evaluateRegressionModel(lirModel, test, "label")
    spark.stop()
  }

}
