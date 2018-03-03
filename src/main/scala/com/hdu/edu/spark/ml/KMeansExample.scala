package com.hdu.edu.spark.ml

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.sql.SparkSession

object KMeansExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()
    val DIR = "E:\\sparkdata\\"
    val dataset = spark.read.format("libsvm").load(DIR +"data\\mllib\\sample_kmeans_data.txt")

    val kmeans = new KMeans().setK(2).setSeed(1L)
    val model = kmeans.fit(dataset)
    val WSSSE = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $WSSSE")
    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)
    spark.stop()
  }

}
