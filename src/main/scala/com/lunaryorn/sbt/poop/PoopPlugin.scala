package sbt

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin
import xsbti.Severity
import xsbti.Position

object PoopPlugin extends AutoPlugin {

  override def requires = JvmPlugin

  override def projectSettings: Seq[Setting[_]] = Seq(
    compilerReporter in (Compile, compile) := {
      Some(new CollectingReporter)
    }
  )

}

case class EmojiProblem(severity: Severity, message: String, position: Position) extends xsbti.Problem {
  def category: String = null
  override def toString = {
    val emoji = if(message.contains("illegal inheritance;") && message.contains("self-type")) {
      
    }
    
    
    s"$position:$severity:$emoji: $message"
  }
}
class CollectingReporter extends xsbti.Reporter {
  val buffer = collection.mutable.ArrayBuffer.empty[xsbti.Problem]

  def reset(): Unit = {
    System.err.println(s"DEBUGME: Clearing errors: $buffer")
    buffer.clear()
  }
  def hasErrors: Boolean = buffer.exists(_.severity == Severity.Error)
  def hasWarnings: Boolean = buffer.exists(_.severity == Severity.Warn)
  def printSummary(): Unit = ()
  def problems: Array[xsbti.Problem] = buffer.toArray

  /** Logs a message. */
  def log(pos: xsbti.Position, msg: String, sev: xsbti.Severity): Unit = {
    val problem = EmojiProblem(sev, msg, pos)
    System.err.println(s"DEBUGME: Logging: $problem")
    buffer.append(problem)
  }

  /** Reports a comment. */
  def comment(pos: xsbti.Position, msg: String): Unit = ()

  override def toString = "CollectingReporter"
}