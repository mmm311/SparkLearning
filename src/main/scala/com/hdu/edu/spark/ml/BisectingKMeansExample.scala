package com.hdu.edu.spark.ml

import org.apache.spark.ml.clustering.BisectingKMeans
import org.apache.spark.sql.SparkSession

object BisectingKMeansExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("BisectingKMeans")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val DIR = "E:\\sparkdata\\"
    val dataset = spark.read.format("libsvm").load( DIR + "data\\mllib\\sample_kmeans_data.txt")
    val bkm = new BisectingKMeans()
      .setK(2).setSeed(1)

    val model = bkm.fit(dataset)
    val cost = model.computeCost(dataset)
    println("Cluster Centers: ")
    val centers = model.clusterCenters
    centers.foreach(println)

    spark.stop()

  }

}
