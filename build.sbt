import sbt.Keys.libraryDependencies

lazy val sparkVersion = "2.3.0"
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.addmeaning.meetup",
      scalaVersion := "2.11.12",
      version := "1.0"
    )),
    name := "k-2-meetup",
    description := "k-2-meetup",
    libraryDependencies  ++= (deps ++ sparkDeps)
  )
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}
run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

lazy val deps = Seq(
  "org.postgresql" % "postgresql" % "42.2.2",
  "com.github.fommil.netlib" % "all" % "1.1.2" % "provided" pomOnly(),
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)
lazy val sparkDeps = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided"
)

