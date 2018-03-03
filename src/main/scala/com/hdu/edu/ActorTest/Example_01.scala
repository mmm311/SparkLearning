package com.hdu.edu.ActorTest

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

object Example_01 {

  class Myactor extends Actor{
    val log = Logging(context.system, this)

    override def receive: Receive = {
      case "test" => log.info("received test")
      case _ => log.info("received unknown message")
    }

  }
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MyActorSystem")
    val systemLog = system.log
    val myactor = system.actorOf(Props[Myactor], name = "myactor")
    systemLog.info("准备向myactor发送消息")
    myactor!"test"
    myactor!123
    system.shutdown()
  }
}