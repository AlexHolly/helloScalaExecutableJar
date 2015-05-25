Tested(25.05.2015) on Windows, sbt 0.13.6, scala 2.11.6, java se 1.8.0_45

# **Build a Scala executable-Jar in three simple steps**

- Add SBT assembly-Plugin

- Add some stuff to build.sbt

- Create Scala executable-Jar

# **Add SBT assembly-Plugin**

There are two ways to add a Plugin.

**Global(use it in any project)**

- home\.sbt\0.13\plugins\plugins.sbt

**Local(my project only)**

- projektfolder\project\plugins.sbt

Don't forget one empty line to seperate plugins

```
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
```

Source: https://github.com/sbt/sbt-assembly :  http://www.scala-sbt.org/

# **Add some stuff to build.sbt**

projectfolder\build.sbt

```
  import AssemblyKeys._
 
  assemblySettings
 
  jarName in assembly := "nameOfOutput.jar"
 
  mergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) =>
     (xs map {_.toLowerCase}) match {
       case ("manifest.mf" :: Nil) | ("index.list" :: Nil) |
            ("dependencies" :: Nil) => MergeStrategy.discard
       case _ => MergeStrategy.discard
     }
    case _ => MergeStrategy.first
  }
...
```

# **Create Scala executable-Jar**

```
cd ..\projectfolder\

sbt assembly
```
**Output**

projectfolder\target\scala-2.11\nameOfOutput.jar.

**Problems?**

There is only one main **“def main(args: Array[String])”** in your project allowed. 

Otherwise you get an error **“no mainmanifestattribut, in nameOfOutput.jar”**

Jar won't start? Try 
```
java -jar nameOfOutput.jar
```
**Missing files?**

Resources/Konfigurations files are placed in nameOfOutput.jar!\

**Solution?**

Use packages and "this.getClass().getResource(...)"


Source: http://raintomorrow.cc/post/50811498259/how-to-package-a-scala-project-into-an

---

# **Merge duplicate files**
```
mergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) =>
     (xs map {_.toLowerCase}) match {
       case ("manifest.mf" :: Nil) | ("index.list" :: Nil) |
            ("dependencies" :: Nil) => MergeStrategy.discard
       case _ => MergeStrategy.discard
     }
    case _ => MergeStrategy.first
  }
```
Source: https://groups.google.com/forum/#!topic/simple-build-tool/ObPlkul0NBk
