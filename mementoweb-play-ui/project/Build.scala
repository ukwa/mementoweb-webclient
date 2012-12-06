import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "bluebox"
    val appVersion      = "1.0.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "uk.bl.wap.memento" % "mementoweb-java-client" % "1.0.0-SNAPSHOT"
      //,
      //"org.archive.heritrix" % "heritrix-commons" % "3.1.0"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
      resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
      //,
      //resolvers += "Internet Archive" at "http://builds.archive.org:8080/maven2"
    )

}
