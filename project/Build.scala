import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "mementos-webclient"
    val appVersion      = "1.0.0-SNAPSHOT"

    val appDependencies = Seq(
      javaCore,
      cache,
      // Add your project dependencies here,
      "uk.bl.wa.memento" % "mementoweb-client-java" % "1.1.6-SNAPSHOT"
    )

val main = play.Project(appName, appVersion, appDependencies).settings(
      // Add your own project settings here
      //resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
      //,
      //resolvers += "Internet Archive" at "http://builds.archive.org:8080/maven2"
    )

}
