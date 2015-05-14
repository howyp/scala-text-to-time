package io.github.howyp.parser

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers

trait RelativeDateParser extends RegexParsers with NumberParsers {
  def now: LocalDate

  def today: Parser[LocalDate] = ("now" | "today") ^^^ now
  def tomorrow: Parser[LocalDate] = "tomorrow" ^^^ now.plusDays(1)
  def yesterday: Parser[LocalDate] = "yesterday" ^^^ now.minusDays(1)
  def `today/tomorrow/yesterday`: Parser[LocalDate] =
    today | tomorrow | yesterday | failure("named day expected") named "namedDay"

  def dayOfWeek: Parser[LocalDate] = (
      ("sunday" ^^^ now.plusDays(1))
    | ("monday" ^^^ now.plusDays(2))
    | ("tuesday" ^^^ now.plusDays(3))
    | ("wednesday" ^^^ now.plusDays(4))
    | ("thursday" ^^^ now.plusDays(5))
    | ("friday" ^^^ now.plusDays(6))
    | ("saturday" ^^^ now.plusDays(7))
    | failure("day of the week expected")
  ) named "dayOfWeek"

  def `x day(s)`: Parser[Int] =
    (positiveInteger ~ "days?".r withFailureMessage "expected a number of days") ^? ({
      case 1     ~ "day"               => + 1
      case count ~ "days" if count > 1 => + count
    }, _ => "incorrect mix of plural and singular")  named "daysWithCount"

  def `x day(s) before/after`: Parser[Int] =
    (`x day(s)` | "day" ^^^ 1) ~ ("before"| "after") map {
      case count ~ "after"  => + count
      case count ~ "before" => - count
    }

  def relativeDay: Parser[LocalDate] = (
      (`x day(s) before/after` ~ `today/tomorrow/yesterday`)
    | (`x day(s) before/after` ~ success(now))
    | (`x day(s)`              ~ success(now))
    | (success(0)              ~ `today/tomorrow/yesterday`)
  ) ^? {
    case count ~ date => date plusDays count
  } withFailureMessage "no relative date found" named "relativeDay"
}
