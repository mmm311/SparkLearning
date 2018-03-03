package com.hdu.edu.spark

import org.apache.spark.SparkContext
import org.apache.spark.graphx.Graph
import org.apache.spark.sql.SparkSession

import scala.math.random

object SparkPi {
  def extract(s: String) = {
    val Pattern = """^.*?(\d+).egonet""".r
    val Pattern(num) = s
    num
  }

  def get_edges_from_line(line: String):Array[(Long, Long)] ={
    val ary = line.split(":")
    val srcId = ary(0).toInt
    val dstIds = ary(1).split(" ")
    val edges = for {
      dstId <- dstIds
      if (dstId != "")
    } yield {
      (srcId.toLong, dstId.toLong)

    }
    if (edges.size > 0) edges else Array((srcId, srcId))
  }

  def make_edges(contents: String) = {
    val lines = contents.split("\n")
    val unflat = for{
      line <- lines
    }yield {
      get_edges_from_line(line)
    }
    val flat = unflat.flatten
    flat
  }

  def get_circles(flat: Array[(Long, Long)], sc: SparkContext) = {
    val edges = sc.makeRDD(flat)
    val g = Graph.fromEdgeTuples(edges, 1)
    val cc = g.connectedComponents()
    cc.vertices.map(x => (x._2, Array(x._1)))
      .reduceByKey((a, b) => a ++ b)
       .values.map(_.mkString(" ")).collect.mkString(";")
  }
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()

    val sc = spark.sparkContext
    val egonets = sc.wholeTextFiles("E:\\data\\egonets")
    val egonet_members = egonets.map(x => extract(x._1)).collect()
    val egonet_edegs = egonets.map(x => make_edges(x._2)).collect()
    val egonet_circles = egonet_edegs.toList.map(x => get_circles(x, sc))
    println("UserId, Prediction")
    val result = egonet_members.zip(egonet_circles).map(x => x._1 + "," + x._2)
    println(result.mkString("\n"))
    spark.stop()
  }

}
