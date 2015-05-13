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

  def daysWithCount: Parser[Int] =
    (positiveInteger ~ "days?".r withFailureMessage "expected a number of days") ^? ({
      case 1     ~ "day"               => + 1
      case count ~ "days" if count > 1 => + count
    }, _ => "incorrect mix of plural and singular")  named "daysWithCount"

  def daysBeforeAfter: Parser[Int] =
    (daysWithCount | "day" ^^^ 1) ~ ("before"| "after") map {
      case count ~ "after"  => + count
      case count ~ "before" => - count
    }

  def relativeDay: Parser[LocalDate] = (
      (daysBeforeAfter ~ namedDay)
    | (daysBeforeAfter ~ success(now))
    | (daysWithCount ~ success(now))
    | (success(0) ~ namedDay)
  ) ^? {
    case count ~ date => date plusDays count
  } withFailureMessage "no relative date found" named "relativeDay"
}
