package com.hdu.edu.spark.graphx

import java.io.PrintWriter

import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object SynthBechmark {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()

    val sc = spark.sparkContext

    GraphGenerators.gridGraph(sc, 4, 4)



  }

}