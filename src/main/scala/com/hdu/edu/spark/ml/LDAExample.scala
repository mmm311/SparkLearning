package com.hdu.edu.spark.ml

import org.apache.spark.ml.clustering.LDA
import org.apache.spark.sql.SparkSession

object LDAExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()
    val IDIR = "E:\\sparkdata\\"
    val dataset = spark.read.format("libsvm")
      .load(IDIR + "/data/mllib/sample_lda_libsvm_data.txt")

    val lda = new LDA().setK(10).setMaxIter(10)
    val model = lda.fit(dataset)

    val ll = model.logLikelihood(dataset)
    val lp = model.logPerplexity(dataset)
    println(s"The lower bound on the log likelihood of the entire corpus: $ll")
    println(s"The upper bound on perplexity: $lp")

    // Describe topics
    val topics = model.describeTopics(3)
    println("The topics described by their top-weighted terms:")
    topics.show(false)

    // Show the result.
    val transformed = model.transform(dataset)
    transformed.show(false)
    spark.stop()
  }
}
