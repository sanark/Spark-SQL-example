package spark.example

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

//This code just copied from shark context testing 
class SharkRunnable(sql: SQLContext, con: SparkContext) extends Runnable{
  val sc = con
  val sqlContext = sql

  def run() {
    sc.setLocalProperty("spark.scheduler.pool", "pool1")
    Query.runQuery(sqlContext, sc)
  }
}

class SharkRunnableTwo(sql: SQLContext, con: SparkContext) extends Runnable{
  val sc = con
  val sqlContext = sql

  def run() {
    sc.setLocalProperty("spark.scheduler.pool", "pool2")
    Query.runQuery2(sqlContext, sc)
  }
}
