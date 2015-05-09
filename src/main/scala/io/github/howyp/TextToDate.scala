package io.github.howyp

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers

trait TextToDate extends RegexParsers {
  def now: LocalDate

  def relativeDay: Parser[LocalDate] = "now" ^^^ now | "tomorrow" ^^^ now.plusDays(1)

  def parse(text: String): LocalDate = parse(relativeDay, text).get
}

object TextToDate extends TextToDate {
  def now = LocalDate.now
  def apply = parse _
}
