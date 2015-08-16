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
    object MyProblem extends xsbti.Problem {
      def category: String = null
      def severity: Severity = sev
      def message: String = msg
      def position: Position = pos
      override def toString = s"$position:$severity: $message"
    }
    System.err.println(s"DEBUGME: Logging: $MyProblem")
    buffer.append(MyProblem)
  }

  /** Reports a comment. */
  def comment(pos: xsbti.Position, msg: String): Unit = ()

  override def toString = "CollectingReporter"
}