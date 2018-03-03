package com.hdu.edu.action

import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession

object MetadataProcess {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()
    val sc = spark.sparkContext
//    val vertices = sc.textFile("E:\\data\\metadata-raw.txt")
//        .flatMap{line => line.split("\\s+")}.distinct()
//    vertices.map{ vertex => vertex.replace("-", "") + "\t" +vertex}
//      .saveAsTextFile("E:\\data\\metadata-lookup")
//    sc.textFile("E:\\data\\metadata-raw.txt").map{line =>
//      var fields = line.split("\\s+")
//      if (fields.length == 2){
//        fields(0).replace("-", "") + "\t" + fields(1).replace("-", "")
//      }
//    }.saveAsTextFile("E:\\data\\metadata-processed")

    val graph = GraphLoader.edgeListFile(sc, "E:\\data\\metadata-processed")
    val ranks = graph.pageRank(0.0001).vertices
    val entities = sc.textFile("E:\\data\\metadata-lookup").map{ line =>
      var fields = line.split("\\s+")
      (fields(0).toLong, fields(1))
    }
    val ranksByVertex = entities.join(ranks).map{
      case (id, (vertex, rank)) => (rank, vertex)
    }

    println(ranksByVertex.sortByKey(false).take(5).mkString("\n"))

  }

}
