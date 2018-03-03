package com.hdu.edu.ActorTest

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}


object Example_07 {
  class FirstActor extends Actor with ActorLogging{
    var child: ActorRef = _

    override def  preStart(): Unit = {
      log.info("preStart() in FirstActor")
      child = context.actorOf(Props[MyActor])
    }
    override def receive: Receive = {
      case x => child !x;log.info("received" + x)
    }
  }

  class MyActor extends Actor with ActorLogging{
    var parentActorRef: ActorRef = _

    override def preStart(): Unit = {
      parentActorRef = context.parent
    }

    override def receive: Receive = {
      case "test" => log.info("received test"); parentActorRef!"message from ParentActorRef"
      case _ => log.info("received unknown message")
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("MyActorSystem")
    val systemLog = system.log
    val myActor = system.actorOf(Props[FirstActor], name = "firstActor")
    val myActorPath = system.child("firstActor")
    val myActor1 = system.actorSelection(myActorPath)
    systemLog.info("准备向myactor发送消息")
    myActor1!"test"
    myActor1!123
    Thread.sleep(5000)
    system.shutdown()
  }




}
