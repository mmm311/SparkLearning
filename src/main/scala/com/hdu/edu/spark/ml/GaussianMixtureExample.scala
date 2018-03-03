package com.hdu.edu.spark.ml

import org.apache.spark.ml.clustering.GaussianMixture
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

/**
  * @author liu
  * 错误代码
  */
object GaussianMixtureExample {
  def main(args: Array[String]): Unit = {

    val local = System.getProperty("user.dir")
    val spark = SparkSession.builder.master("local").appName("example").
      config("spark.sql.warehouse.dir", s"file:///${local}\\spark-warehouse").config("spark.sql.shuffle.partitions", "20").getOrCreate()

    val dataset = spark.read.format("libsvm").load("hdfs:10.1.18.20:8020")

    // Trains Gaussian Mixture Model
    val gmm = new GaussianMixture()
      .setK(2)
    val model = gmm.fit(dataset)

    // output parameters of mixture model model
    for (i <- 0 until model.getK) {
      println(s"Gaussian $i:\nweight=${model.weights(i)}\n" +
        s"mu=${model.gaussians(i).mean}\nsigma=\n${model.gaussians(i).cov}\n")
    }
    // $example off$

    spark.stop()

  }

}
