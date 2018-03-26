import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkApp extends App {
  val session = SparkSession.builder()
    .appName("spark-app").master("local[*]").getOrCreate()

  import session.implicits._
  case class Test(id: Int, s: String)

 Utils.downloadFile()
  Utils.unzipFile()

  session.read.csv("beer-recipes/recipeData.csv").show(10)
//  val df = Seq(
//    Test(1, "q"),
//    Test(2, "w"),
//    Test(3, "e"),
//    Test(4, "r")
//  ).toDF
//
//
//    df.write.format("jdbc")
//      .option("url", "jdbc:postgresql://35.187.161.98:5432/meetup")
//      .option("user", "postgres")
//      .option("password", "hello-spark-kuberentes")
//      .option("schema", "public")
//      .option("dbtable", "df")
//      .option("driver", "org.postgresql.Driver")
//        .mode(SaveMode.Overwrite)
//      .save()

  session.close()
}
object Utils{
  def downloadFile() {
    import sys.process._
    import java.net.URL
    import java.io.File
    new URL("https://www.kaggle.com/jtrofe/beer-recipes/downloads/beer-recipes.zip/2") #> new File("beer-recipes.zip") !!
  }
  def unzipFile(): String ={
    import sys.process._
    "tar -xvzf beer-recipes.zip" !!
  }
}