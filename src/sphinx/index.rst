.. title: SBT Poop

.. raw:: html

  <div class="jumbotron" style="margin-top:-20px">
          <h1>SBT Poop</h1>
          <p>Enjoy emojis in your compiler errors!</p>
  </div>

  <div class="row">
    <div class="col-md-6">
      <h2>Installation</h2>
      <p>Add the plugin to your <em>plugins.sbt</em></p>
      <pre>
  resolvers += Resolver.url(
    "team-boris",
    url("http://dl.bintray.com/team-boris/sbt-plugins")
  )(Resolver.ivyStylePatterns)

  addSbtPlugin("de.teamboris" % "sbt-poop" % "0.1.1")
      </pre>
    </div>
    <div class="col-md-6">
      <h2>Scaladocs</h2>
      <p><a href="latest/api" role="button">Read Docs »</a></p>
   </div>
  </div>
