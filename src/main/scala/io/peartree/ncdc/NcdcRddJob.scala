package io.peartree.ncdc

import org.apache.spark._


object NcdcRddJob {

  def main(args: Array[String]) {
    // val inputFile = args(0)
    val inputFile = "/home/rgomes/workspace/spark-rdd-ncdc/src/test/resources/1901.txt"
    val outputFile = "/tmp/"

    println(inputFile)
    println(outputFile)

    val conf = new SparkConf().setAppName("ncdc-rdd")
    val sc = new SparkContext(conf)

    // Load our input data
    val inputRDD = sc.textFile(inputFile)

    // do the work
    val weatherPairRDD =
      inputRDD
        .map {
          case line =>
            val quality: String = line.substring(92, 93)
            val sign: Int = if(line.charAt(87) == '+') 1 else -1
            val temp: Int = line.substring(88, 92).toInt
            val year: Int = line.substring(15, 19).toInt
            (quality, year, sign, temp) }
        .filter { case (q, _, _, t ) => q.matches("[01459]") && t != 9999}
        .map { case (_, y, s, t) => ( y, s*t) }
        .reduceByKey({ case (prev, curr) => Math.max(prev, curr) })
        .saveAsTextFile(outputFile)
  }

}
