// in order to access private compilerReporter task
package sbt

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin
import com.lunaryorn.sbt.poop.EmojiReporter

/**
 * 
 */
object PoopPlugin extends AutoPlugin {

  override def requires = JvmPlugin

  override def projectSettings: Seq[Setting[_]] = Seq(
    compilerReporter in (Compile, compile) := {
      val log = streams.value.log
      val src = sourceDirectory.value
      Some(new EmojiReporter(log, src))
    })

}
