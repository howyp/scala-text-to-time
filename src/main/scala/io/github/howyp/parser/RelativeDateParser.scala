package io.github.howyp.parser

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers

trait RelativeDateParser extends RegexParsers with NumberParsers {
  def now: LocalDate

  def today: Parser[LocalDate] = ("now" | "today") ^^^ now
  def tomorrow: Parser[LocalDate] = "tomorrow" ^^^ now.plusDays(1)
  def yesterday: Parser[LocalDate] = "yesterday" ^^^ now.minusDays(1)
  def namedDay: Parser[LocalDate] =
    today | tomorrow | yesterday | failure("named day expected") named "namedDay"

  def daysBeforeAfter: Parser[Int] =
    opt(positiveInteger).map(_.getOrElse(1)) ~ "days?".r ~ opt("before"| "after") map {
      case count ~ _ ~ (None | Some("after")) => + count
      case count ~ _ ~ Some("before")         => - count
    }

  def relativeDay: Parser[LocalDate] = daysBeforeAfter.? ~ namedDay.? ^? {
    case Some(count) ~ Some(date) => date plusDays count
    case None        ~ Some(date) => date
    case Some(count) ~ None       => now  plusDays count
  } withFailureMessage "no relative date found" named "relativeDay"
}
