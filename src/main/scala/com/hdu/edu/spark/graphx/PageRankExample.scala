package com.hdu.edu.spark.graphx

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession

object PageRankExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()

    val sc = spark.sparkContext

    val graph = GraphLoader.edgeListFile(sc,"E:\\sparkdata\\data\\graphx\\followers.txt")
    val ranks = graph.pageRank(0.0001).vertices
    val users = sc.textFile("E:\\sparkdata\\data\\graphx\\users.txt")
      .map{line =>
        val fields = line.split(",")
        (fields(0).toLong, fields(1))
      }

    val ranksByUsername = users.join(ranks).map{
      case (id, (username, rank)) => (username, rank)
    }
    println(ranksByUsername.collect().mkString("\n"))
    spark.stop()
  }


}
