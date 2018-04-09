package com.hdu.edu.spark.stream

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.SparkConf
import org.apache.spark.internal.Logging
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

object CustomReceiver {
  def main(args: Array[String]): Unit = {
//    if (args.length < 2){
//      System.err.println("Usage: CustomReceiver <hostname> <port>")
//      System.exit(1)
//    }

    val sparkConf = new SparkConf().setMaster("local")
      .setAppName("CustomReceiver")

    val ssc = new StreamingContext(sparkConf, Seconds(1))

    val lines = ssc.receiverStream(new CustomReceiver("10.1.18.84", 9999))
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x , 1)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }

}

class CustomReceiver(host: String, port: Int)
  extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2) with Logging{
  override def onStart(){
    new Thread("Socket Recevier"){
      override def run() = {receive()}
    }.start()
  }

  override def onStop(): Unit = ???

  private def receive(): Unit ={
    var socket: Socket = null
    var userInput: String = null

    try{
      logInfo(s"Connecting to $host : $port")
      socket = new Socket(host, port)
      logInfo(s"Connected to $host: $port")

      val reader = new BufferedReader(
        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
      )
      userInput = reader.readLine()
      while (!isStopped && userInput != null){
        store(userInput)
        userInput = reader.readLine()
      }
      reader.close()
      socket.close()
      logInfo("Stop receiving")
      restart("Trying to connect again")
    }catch {
      case e : java.net.ConnectException =>
        restart(s"Error connecting to $host: $port", e)
      case t: Throwable =>
        restart("Error receving data", t)
    }
  }
}