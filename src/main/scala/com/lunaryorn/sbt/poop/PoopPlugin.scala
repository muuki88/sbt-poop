package sbt

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin
import xsbti.{ Severity, Position }

import com.typesafe.emoji.ShortCodes.Implicits._
import com.typesafe.emoji.ShortCodes.Defaults._

object PoopPlugin extends AutoPlugin {

  override def requires = JvmPlugin

  override def projectSettings: Seq[Setting[_]] = Seq(
    compilerReporter in (Compile, compile) := {
      val log = streams.value.log
      Some(new CollectingReporter(log))
    })

}

case class EmojiProblem(severity: Severity, message: String, position: Position) extends xsbti.Problem {
  def category: String = null

  override def toString = {
    val emoji = if (message.contains("illegal inheritance;") && message.contains("self-type")) {
      "poop".emoji.toString
    } else {
      "cry".emoji.toString
    }

    s" $emoji [$position] $message"
  }
}
class CollectingReporter(log: Logger) extends xsbti.Reporter {
  val buffer = collection.mutable.ArrayBuffer.empty[xsbti.Problem]

  def reset(): Unit = {
    buffer.clear()
  }
  def hasErrors: Boolean = buffer.exists(_.severity == Severity.Error)
  def hasWarnings: Boolean = buffer.exists(_.severity == Severity.Warn)
  def printSummary(): Unit = ()
  def problems: Array[xsbti.Problem] = buffer.toArray

  /** Logs a message. */
  def log(pos: xsbti.Position, msg: String, sev: xsbti.Severity): Unit = {
    val problem = EmojiProblem(sev, msg, pos)
    sev match {
      case xsbti.Severity.Error =>
        buffer.append(problem)
        log.error(s"$problem")
      case xsbti.Severity.Warn => log.warn(s"$problem")
      case xsbti.Severity.Info => log.info(s"$problem")
    }
  }

  /** Reports a comment. */
  def comment(pos: xsbti.Position, msg: String): Unit = ()

  override def toString = "CollectingReporter"
}
