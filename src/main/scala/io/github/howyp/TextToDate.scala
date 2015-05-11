package io.github.howyp

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers

trait TextToDate extends RegexParsers {
  def now: LocalDate

  def integer: Parser[Int] = "\\d+".r ^^ (_.toInt)

  def today: Parser[LocalDate] = ("now" | "today") ^^^ now
  def tomorrow: Parser[LocalDate] = "tomorrow" ^^^ now.plusDays(1)
  def yesterday: Parser[LocalDate] = "yesterday" ^^^ now.minusDays(1)

  def daysBeforeAfter: Parser[Int] = opt(integer).map(_.getOrElse(1)) ~ "days?".r ~ opt("before"| "after") map {
    case count ~ _ ~ (None | Some("after")) => + count
    case count ~ _ ~ Some("before")         => - count
  }

  def relativeDay: Parser[LocalDate] = opt(daysBeforeAfter) ~ opt(today | tomorrow | yesterday) map {
    case None               ~ Some(date) => date
    case Some(dayIncrement) ~ Some(date) => date plusDays dayIncrement
    case Some(dayIncrement) ~ None       => now  plusDays dayIncrement
  }
}

object TextToDate extends TextToDate {
  def now = LocalDate.now
}
