package com.hdu.edu.spark.streaming.clickstream

import java.io.PrintWriter
import java.net.ServerSocket

import scala.util.Random

class PageView(val url: String, val status: Int,
               val zipCode: Int, val userID: Int) extends Serializable {
  override def toString: String = {
    "%s\t%s\t%s\t%s\n".format(url, status, zipCode, userID)
  }
}

object PageView extends Serializable {
  def fromString(in: String): PageView = {
    val parts = in.split("\t")
    new PageView(parts(0), parts(1).toInt, parts(2).toInt, parts(3).toInt)
  }
}

object PageViewGenerator {
  val pages = Map("http://foo.com/" -> .7,
    "http://foo.com/news" -> 0.2,
    "http://foo.com/contact" -> .1)

  val httpStatus = Map(200 -> 0.95, 404 -> 0.05)
  val userZipCode = Map(94709 -> 0.5, 94117 -> 0.5)
  val userID = Map((1 to 100).map(_ -> 0.01):_*)

  def pickFromDistribution[T](inputMap: Map[T, Double]) : T = {
    val rand = new Random().nextDouble()
    var total = 0.0
    for ((item, prob) <- inputMap){
      total = total + prob
      if (total > rand){
        return item
      }
    }
    inputMap.take(1).head._1
  }

  def getNextClickEvent(): String = {
    val id = pickFromDistribution(userID)
    val page = pickFromDistribution(pages)
    val status = pickFromDistribution(httpStatus)
    val zipCode = pickFromDistribution(userZipCode)
    new PageView(page, status, zipCode, id).toString()
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 2){
      System.err.println("Usage: PageViewGenerator <port> <viewsPerSecond>")
      System.exit(1)
    }

    val port = args(0).toInt
    val viewsPerSecond = args(1).toFloat
    val sleepDelayMs = (1000 / viewsPerSecond).toInt
    val listener = new ServerSocket(port)
    println("Listening on port: " + port)

    while (true){
      val socket = listener.accept()
      new Thread(){
        override def run(): Unit = {
          println("Got client connected from: " + socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream, true)

          while (true){
            Thread.sleep(sleepDelayMs)
            out.write(getNextClickEvent())
            out.flush()
          }
          socket.close()
        }
      }.start()

    }
  }
}