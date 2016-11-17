package io.peartree.ncdc

import org.apache.spark._

object NcdcRddJob {
  def main(args: Array[String]) {

    //---------------------------------------------------------------------------------
    // article: https://dzone.com/articles/introduction-apache-spark
    // download data from: https://www.ncdc.noaa.gov/orders/qclcd/QCLCD200705.zip
    //---------------------------------------------------------------------------------

    val home = System.getProperty("user.home")
    val inputFile  = if(args.size > 0) args(0) else s"${home}/tmp/ncdc/200705hourly.txt";
    val outputFile = if(args.size > 1) args(1) else {
      val d = new java.util.Date
      val fmt = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss")
      val name = fmt.format(d)
      s"${home}/tmp/${name}"
    }

    val f = new java.io.File(inputFile)
    if(!f.exists()) throw new RuntimeException(s"File does not exist: ${inputFile}")

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

