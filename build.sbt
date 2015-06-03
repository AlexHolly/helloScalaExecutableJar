jarName in assembly := "helloScalaExecutableJar.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(cacheUnzip = false)

mergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) =>
     (xs map {_.toLowerCase}) match {
       case ("manifest.mf" :: Nil) | ("index.list" :: Nil) |
            ("dependencies" :: Nil) => MergeStrategy.discard
       case _ => MergeStrategy.discard
     }
    case _ => MergeStrategy.first
}

name := "helloScalaExecutableJar"

organization := "helloscalaexecutablejar"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")
