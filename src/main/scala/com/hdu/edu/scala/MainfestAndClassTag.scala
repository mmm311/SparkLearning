package com.hdu.edu.scala

import scala.reflect.ClassTag

object MainfestAndClassTag {
  def arrayMake[T : Manifest](first: T, second : T) = {
    val r = new Array[T](2)
    r(0) = first
    r(1) = second
    r
  }

  def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)
  def main(args: Array[String]): Unit = {
    arrayMake(1, 2).foreach(println)
    mkArray("Japan", "Brazil", "Germany").foreach(println)
  }

}
