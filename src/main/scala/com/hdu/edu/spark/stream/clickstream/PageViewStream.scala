package com.hdu.edu.spark.stream.clickstream

import com.hdu.edu.spark.stream.StreamingExample
import org.apache.spark.streaming.{Seconds, StreamingContext}

object PageViewStream {
  def main(args: Array[String]): Unit = {
    if (args.length != 3){
      System.err.println("Usage: PageViewStream <metric> <host> <port>")
      System.err.println("<metric> must be one of pageCounts, slidingPageCount, " +
        " errorRatePerZipCode, activeUserCount, popularUsersSeen")
      System.exit(1)
    }

    StreamingExample.setStreamingLogLevels()
    val metric = args(0)
    val host = args(1)
    val port = args(2).toInt

    val ssc = new StreamingContext("local", "PageViewStream", Seconds(1),
      System.getenv("SPARK_HOME"), StreamingContext.jarOfClass(this.getClass).toSeq)

    val pageViews = ssc.socketTextStream(host, port)
      .flatMap(_.split("\n"))
      .map(PageView.fromString(_))

    val pageCounts = pageViews.map(view => view.url).countByValue()
    val slidingPageCounts = pageViews.map(view => view.url)
      .countByValueAndWindow(Seconds(10), Seconds(2))

    val statusesPerZipCode = pageViews.window(Seconds(30), Seconds(2))
      .map(view => ((view.zipCode, view.status)))
      .groupByKey()

    val errorRatePerZipCode = statusesPerZipCode.map{
      case(zip, statuses) =>
        val normalCount = statuses.count(_ == 200)
        val errorCount = statuses.size - normalCount
        val errorRatio = errorCount.toFloat / statuses.size
        if (errorRatio > 0.05) {
          "%s: **%s**".format(zip, errorRatio)
        }else{
          "%s: %s".format(zip, errorRatio)
        }
    }

    val activeUserCount = pageViews.window(Seconds(15), Seconds(2))
      .map(view => (view.userID, 1))
      .groupByKey()
      .count()
      .map("Unique active users: " + _)

    val userList = ssc.sparkContext.parallelize(Seq(
      1 -> "Patrick Wendell",
      2 -> "Reynold Xin",
      3 -> "Matei Zaharia"
    ))

//    metric match {
//      case "pageCounts" => pageCounts.print()
//      case "slidingPageCounts" => slidingPageCounts.print()
//      case "errorRatePerZipCode" => errorRatePerZipCode.print()
//      case "activeUserCount" => activeUserCount.print()
//      case "popularUsersSeen" =>
//        pageViews.map(view => (view.userID, 1))
//          .foreachRDD((rdd, time) => rdd.join(userList))
//          .map(_._2._2)
//          .take(10)
//          .foreach(u => println("Saw user %s at time %s".format(u, time)))
//      case _ => println("Invalid metric entered: " + metric)
//    }

    ssc.start()
    ssc.awaitTermination()
  }

}
