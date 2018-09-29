package com.hdu.edu.action

class ApplyTest {
  def apply () = {
    println("This is an class, apply() ...")
  }
}

object ApplyTest{
  def apply() = {
    println("This is an object, apply() ...")
    new ApplyTest()
  }

  def main(args: Array[String]): Unit = {
    var at = ApplyTest()
    at()
  }
}

