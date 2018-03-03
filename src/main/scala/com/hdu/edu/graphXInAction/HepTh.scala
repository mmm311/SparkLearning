package com.hdu.edu.graphXInAction

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession

object HepTh {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local")
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()

    val sc = spark.sparkContext
    val graph = GraphLoader.edgeListFile(sc, "E:\\data\\Cit-HepTh.txt")
    val result = graph.pageRank(0.001).vertices

    println(result.reduce((a, b) => if (a._2 > b._2) a else b))

  }

}
