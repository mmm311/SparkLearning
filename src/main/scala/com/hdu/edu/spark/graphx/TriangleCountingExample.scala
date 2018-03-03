package com.hdu.edu.spark.graphx

import org.apache.spark.graphx.{Edge, Graph, GraphLoader, PartitionStrategy}
import org.apache.spark.sql.SparkSession

object TriangleCountingExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()
    val sc = spark.sparkContext
    val path = "E:\\sparkdata\\data\\graphx\\"

    val graph = GraphLoader.edgeListFile(sc, path + "followers.txt", true)
      .partitionBy(PartitionStrategy.RandomVertexCut)

    val triCounts = graph.triangleCount().vertices
    val users = sc.textFile(path + "users.txt").map{
      line =>
        val fields = line.split(",")
        (fields(0).toLong, fields(1))
    }
    val triCountByUsername = users.join(triCounts).map{
      case(id, (username, tc)) => (username, tc)
    }
    println(triCountByUsername.collect().mkString("\n"))
    spark.stop()




  }

}
