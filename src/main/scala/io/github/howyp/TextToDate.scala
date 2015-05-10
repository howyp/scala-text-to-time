package io.github.howyp

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers

trait TextToDate extends RegexParsers {
  def now: LocalDate

  def today: Parser[LocalDate] = ("now" | "today") ^^^ now
  def tomorrow: Parser[LocalDate] = "tomorrow" ^^^ now.plusDays(1)
  def yesterday: Parser[LocalDate] = "yesterday" ^^^ now.minusDays(1)

  def daysBeforeAfter: Parser[Int] = "day before" ^^^ -1

  def relativeDay: Parser[LocalDate] = opt(daysBeforeAfter) ~ (today | tomorrow | yesterday) map {
    case None ~ date               => date
    case Some(dayIncrement) ~ date => date plusDays dayIncrement
  }

  def parse(text: String): LocalDate = parse(relativeDay, text).get
}

object TextToDate extends TextToDate {
  def now = LocalDate.now
  def apply = parse _
}
