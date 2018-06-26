name := "flume-json-interceptor"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.apache.flume" % "flume-ng-dist" % "1.8.0",
  "com.lihaoyi" % "upickle_2.12" % "0.6.6",
  "com.lihaoyi" % "ujson_2.12" % "0.6.6"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}