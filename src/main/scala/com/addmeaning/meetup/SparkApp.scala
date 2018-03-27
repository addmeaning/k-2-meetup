package com.addmeaning.meetup

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkApp extends App {
  val session = SparkSession.builder()
    .appName("spark-app").master("local[*]").getOrCreate()

  import session.implicits._
  case class Test(id: Int, s: String)


  val df = Seq(
    Test(1, "q"),
    Test(2, "w"),
    Test(3, "e"),
    Test(4, "r")
  ).toDF


    df.write.format("jdbc")
      .option("url", "jdbc:postgresql://35.187.161.98:5432/meetup")
      .option("user", "postgres")
      .option("password", "hello-spark-kuberentes")
      .option("schema", "public")
      .option("dbtable", "df")
      .option("driver", "org.postgresql.Driver")
        .mode(SaveMode.Overwrite)
      .save()

  session.close()
}
