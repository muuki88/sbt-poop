lazy val root = Project("plugins", file(".")) dependsOn(poop)

lazy val poop = file("..").getAbsoluteFile.toURI
