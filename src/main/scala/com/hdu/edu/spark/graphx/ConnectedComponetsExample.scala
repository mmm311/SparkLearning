package com.hdu.edu.spark.graphx

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession

object ConnectedComponetsExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()

    val sc = spark.sparkContext
    val graph = GraphLoader.edgeListFile(sc, "E:\\sparkdata\\data\\graphx\\followers.txt")
    val cc = graph.stronglyConnectedComponents(20).vertices

    val users = sc.textFile("E:\\sparkdata\\data\\graphx\\users.txt").map{
      line =>
        val fields = line.split(",")
        (fields(0).toLong, fields(1))
    }
    val ccByUsername = users.join(cc).map{
      case (id, (username, cc)) => (username, cc)
    }

    println(ccByUsername.collect().mkString("\n"))
    spark.stop()

  }

}
