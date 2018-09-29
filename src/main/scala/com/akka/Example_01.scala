package com.akka

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

object Example_01 {

  class MyActor extends Actor {
    val log = Logging(context.system, this)

    def receive = {
      case "test" => log.info("received test")
      case _ => log.info("received unkown message")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MyActorSystem")

    val systemLog = system.log

    val myactor = system.actorOf(Props[MyActor], name = "myactor")

    systemLog.info("准备向myactor 发送消息")

    myactor! "test"
    myactor! "123"

    system.shutdown()
  }

}
