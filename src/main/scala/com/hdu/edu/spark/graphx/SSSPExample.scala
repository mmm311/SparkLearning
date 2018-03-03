package com.hdu.edu.spark.graphx

import org.apache.spark.graphx.{Graph, VertexId}
import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.sql.SparkSession

object SSSPExample {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local")
      .getOrCreate()
    val sc = spark.sparkContext

    val graph: Graph[Long, Double] =
      GraphGenerators.logNormalGraph(sc, numVertices = 100)
      .mapEdges(e => e.attr.toDouble)
    val sourceId: VertexId = 42
    val initialGraph = graph.mapVertices((id, _) =>
    if (id == sourceId) 0.0
    else Double.PositiveInfinity
    )
    println(Double.PositiveInfinity)
    val sssp = initialGraph.pregel(Double.PositiveInfinity)(
      (id, dist, newDist) => math.min(dist, newDist),
      triplet => {
        if (triplet.srcAttr + triplet.attr < triplet.dstAttr){
          Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
        }else{
          Iterator.empty
        }
      },
      (a, b) => math.min(a, b)
    )
  println(sssp.vertices.collect.mkString("\n"))
    spark.stop()
  }

}
