package spark.example

import java.io._

// Spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object Main {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("test").setMaster("local")
    conf.set("spark.eventLog.enabled", "true")
    conf.set("spark.eventLog.dir", "file:///home/shlee0605/shlee_test/spark-sql/event_log")
    //conf.setJars(Seq("target/scala-2.10/spark-example-assembly-0.1.0.jar"))
    conf.set("spark.scheduler.mode", "FAIR")
    conf.set("spark.scheduler.allocation.file", "conf/internal.xml")
    
    val writer = new PrintWriter(new File("log/test.txt"))

    val sc = new SparkContext(conf)
    val sqlContext = Query.createTables(sc)


    sc.setLocalProperty("spark.scheduler.pool", "pool1")

    writer.write("Pool1, Submit time: " + System.currentTimeMillis() + "\n")
    Query.runQuery(sqlContext)

    sc.setLocalProperty("spark.scheduler.pool", "pool2")

    writer.write("Pool2, Submit time: " + System.currentTimeMillis() + "\n")
    Query.runQuery2(sqlContext)
    writer.close()
    System.exit(0)
  }
}
