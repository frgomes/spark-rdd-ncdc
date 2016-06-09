package io.peartree.ncdc

import org.apache.spark._
import org.apache.spark.SparkContext._

object NcdcRddJob {

  def main(args: Array[String]) {
    val inputFile = args(0)
    val outputFile = args(0)

    val conf = new SparkConf()
      .setAppName("ncdc-rdd")
    val sc = new SparkContext(conf)


    // Load our input data
    val input =  sc.textFile(inputFile)

    // do the work
    val counts = input.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}

    // save the result
    counts.saveAsTextFile(outputFile)
  }

}
