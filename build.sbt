name := "sbt-poop"
homepage := Some(url("http://github.com/team-boris/sbt-poop"))
organization := "de.team-boris"
organizationName := "Team Boris"
organizationHomepage := Some(url("http://www.team-boris.de"))

pomExtra := <scm>
  <url>git@github.com:team-boris/sbt-poop.git</url>
  <connection>scm:git@github.com:team-boris/sbt-poop.git</connection>
  </scm>
  <developers>
  <developer>
  <id>muuki88</id>
  <name>Nepomuk Seiler</name>
  <url>http://www.mukis.de</url>
  </developer>
  <developer>
  <id>lunaryorn</id>
  <name>Sebastian Wiesner</name>
  <url>http://www.lunaryorn.com</url>
  </developer>
  </developers>

sbtPlugin := true

libraryDependencies += "com.typesafe" %% "emoji" % "1.0.0"

// bintray settings
bintrayOrganization := Some("team-boris")
bintrayPackageLabels := Seq("hipster", "sbt", "emoji", "scala")
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

// release
releasePublishArtifactsAction := PgpKeys.publishSigned.value
