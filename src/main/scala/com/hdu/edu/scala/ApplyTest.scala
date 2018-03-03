package com.hdu.edu.scala

class ApplyTest {
  def apply() = "This apply is in class"
  def test: Unit ={
    println("test")
  }
}

object ApplyTest{
  var count = 0

  def apply() = new ApplyTest
  def static: Unit = {
    println("I am a static method")
  }
  def incr = {
    count = count + 1
  }

  def main(args: Array[String]): Unit = {
    val a = ApplyTest()
    a.test

  }
}


