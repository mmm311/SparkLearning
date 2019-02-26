package com.hdu.edu.spark

import org.apache.spark.sql.SparkSession

object BroadcastTest {
  def main(args: Array[String]): Unit = {
    val blockSize = if (args.length > 2) args(2) else "4096"

    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .config("spark.broadcat.blockSize", blockSize)
      .getOrCreate()

    val sc = spark.sparkContext

    val slice = if (args.length > 0) args(0).toInt else 1
    val num = if (args.length > 1) args(1).toInt else 100000000

    val arr1 = (0 until num).toArray
    for (i <- 0 until 3){
      println("Iteration " + i)
      println("==============")
      val startTime = System.nanoTime()
      val barr1 = sc.broadcast(arr1)
      val observedSizes = sc.parallelize(1 to 10, slice)
        .map(_ => barr1.value.length)

      observedSizes.repartition(2).coalesce(1).collect().foreach(i => println(i))
      println("Iteration %d took %.0f milliseconds".format(i, (System.nanoTime() - startTime) /1E6))
    }
    spark.stop()
  }

}
