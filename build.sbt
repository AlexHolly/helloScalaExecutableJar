name := "helloScalaExecutableJar"

organization := "helloscalaexecutablejar"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")

assemblyJarName in assembly := name.value +".jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(cacheUnzip = false)

mainClass in assembly := Some("helloscalaexecutablejar.Main") //optional

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case "application.conf"                            => MergeStrategy.concat
  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
