package io.github.howyp.parser

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers
import DayOfWeek._

trait RelativeDateParser extends RegexParsers with NumberParsers with DayOfWeekParser {
  def now: LocalDate

  def today: Parser[LocalDate] = ("now" | "today") ^^^ now
  def tomorrow: Parser[LocalDate] = "tomorrow" ^^^ now.plusDays(1)
  def yesterday: Parser[LocalDate] = "yesterday" ^^^ now.minusDays(1)
  def `today/tomorrow/yesterday/___day`: Parser[LocalDate] =
    today | tomorrow | yesterday | dayOfWeekFromNow | failure("named day expected") named "namedDay"

  def dayOfWeekFromNow: Parser[LocalDate] =
    dayOfWeek ^^ { parsed =>
      now.plusDays(DayOfWeek.daysBetween(DayOfWeek.today, parsed))
    } named "dayOfWeekFromNow"

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
      (`x day(s) before/after` ~ `today/tomorrow/yesterday/___day`)
    | (`x day(s) before/after` ~ success(now))
    | (`x day(s)`              ~ success(now))
    | (success(0)              ~ `today/tomorrow/yesterday/___day`)
  ) ^? {
    case count ~ date => date plusDays count
  } withFailureMessage "no relative date found" named "relativeDay"
}
