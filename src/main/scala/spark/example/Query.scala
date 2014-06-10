package spark.example

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

case class Person(name: String, age: Int)

//This code just copied from shark context testing 
object Query {

  def createTables(sc: SparkContext) : SQLContext = {
    val sqlContext = new SQLContext(sc)
    import sqlContext._

    val people = sc.textFile("file:///home/shlee0605/shlee_test/spark-sql/input/people.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt)) 
    people.registerAsTable("people")
    cacheTable("people")
    sqlContext
  }

  def runQuery(sqlContext: SQLContext) {
    val sum = sqlContext.sql("SELECT name, sum(age) FROM people GROUP BY name").collect.foreach(println)
  }

  def runQuery2(sqlContext: SQLContext) {
    val sum = sqlContext.sql("SELECT name FROM people").collect.foreach(println)
  }
}
