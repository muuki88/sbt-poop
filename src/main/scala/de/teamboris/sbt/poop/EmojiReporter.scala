package de.teamboris.sbt.poop

import sbt._
import xsbti.{ Severity, Position, Reporter, Problem }

import com.typesafe.emoji.ShortCodes.Implicits._
import com.typesafe.emoji.ShortCodes.Defaults._

case class EmojiProblem(severity: Severity, message: String, position: Position) extends Problem {

  /** this method will give the category emoji depending on the error type */
  def category: String = {
    val emoji = if (message.contains("illegal inheritance;") && message.contains("self-type")) {
      "poop".emoji
    } else if (message contains "is already defined as trait") {
      "unamused".emoji
    } else if (message contains "unclosed string literal") {
      "grimacing".emoji
    } else {
      "cry".emoji
    }
    emoji.toString
  }

  override def toString = {
    s" $category [$position] $message"
  }
}
class EmojiReporter(log: Logger, srcDir: File) extends Reporter {
  val buffer = collection.mutable.ArrayBuffer.empty[Problem]

  def reset(): Unit = {
    buffer.clear()
  }
  def hasErrors: Boolean = buffer.exists(_.severity == Severity.Error)
  def hasWarnings: Boolean = buffer.exists(_.severity == Severity.Warn)
  def printSummary(): Unit = ()
  def problems: Array[Problem] = buffer.toArray

  /** Logs a message. */
  def log(pos: Position, msg: String, sev: Severity): Unit = {
    val problem = EmojiProblem(sev, msg, pos)
    sev match {
      case Severity.Error =>
        buffer.append(problem)
        log(problem, Level.Error)
      case Severity.Warn => log(problem, Level.Warn)
      case Severity.Info => log(problem, Level.Info)
    }
  }

  /** Reports a comment. */
  def comment(pos: Position, msg: String): Unit = {
    log.debug(s"[COMMENT] $pos $msg")
  }

  private def log(problem: EmojiProblem, level: Level.Value) {
    val pos = problem.position

    val src = pos.sourceFile()
      .flatMap(_ relativeTo srcDir)
      .map(_.getPath)

    val srcInfo = Seq(
      src,
      pos.line.map(_.toString),
      pos.offset.map(_.toString)
    ).flatten mkString ":"

    log.log(level, s"$srcInfo ${problem.category} ${problem.message}")
    log.log(level, pos.lineContent)

    pos.pointerSpace().foreach { space =>
      log.log(level, space + "point_up_2".emoji)
    }
  }

  override def toString = "EmojiReporter"
}

