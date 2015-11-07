[ ![Download](https://api.bintray.com/packages/team-boris/sbt-plugins/sbt-poop/images/download.svg) ](https://bintray.com/team-boris/sbt-plugins/sbt-poop/_latestVersion)

## SBT Poop

Making compiler warnings more emoji! 

## Enable

In your `plugins.sbt` add

```scala
// adding the repo
resolvers += Resolver.url(
  "team-boris",
  url("http://dl.bintray.com/team-boris/sbt-plugins"))(
  Resolver.ivyStylePatterns
 )

addSbtPlugin("de.teamboris" % "sbt-poop" % "0.1.1")
```

