name := "sbt-poop"
organization := "de.teamboris"
version := "0.1"

sbtPlugin := true

libraryDependencies += "com.typesafe" %% "emoji" % "1.0.0"

// bintray settings
bintrayOrganization := Some("team-boris")
bintrayPackageLabels := Seq("hipster", "sbt", "emoji", "scala")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))
