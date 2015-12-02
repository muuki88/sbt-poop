[![Download](https://api.bintray.com/packages/team-boris/sbt-plugins/sbt-poop/images/download.svg) ](https://bintray.com/team-boris/sbt-plugins/sbt-poop/_latestVersion)
[![License MIT](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/team-boris/sbt-poop/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/team-boris/sbt-poop.svg?branch=master)](https://travis-ci.org/team-boris/sbt-poop)
[![Waffle Board](https://img.shields.io/badge/Board-ready-75AED9.svg)](https://waffle.io/team-boris/sbt-poop)

SBT 💩
=====

Emoji for your SBT! 😍

Setup
-----

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
