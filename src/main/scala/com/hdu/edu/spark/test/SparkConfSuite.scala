package com.hdu.edu.spark.test


import org.scalatest.{FlatSpec, FunSuite}
class SparkConfSuite extends FlatSpec{
  def sayHello = "Hello"

  "Test A" should "Given is Equal with return" in {
    assert("Hello" equals(sayHello))
  }
  "Test B" should "Given is not Equal with return" in{
    assert("World" equals(sayHello))
  }


}
