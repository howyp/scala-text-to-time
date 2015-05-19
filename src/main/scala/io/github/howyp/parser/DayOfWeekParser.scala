package io.github.howyp.parser

import scala.util.parsing.combinator.RegexParsers

object DayOfWeekParser extends DayOfWeekParser

trait DayOfWeekParser extends RegexParsers {
  import DayOfWeek._

  def dayOfWeek: Parser[DayOfWeek] = (
    ("monday" | "Monday") ^^^ Monday
    | ("tuesday" | "Tuesday") ^^^ Tuesday
    | ("wednesday" | "Wednesday") ^^^ Wednesday
    | ("thursday" | "Thursday") ^^^ Thursday
    | ("friday" | "Friday") ^^^ Friday
    | ("saturday" | "Saturday") ^^^ Saturday
    | ("sunday" | "Sunday") ^^^ Sunday
    | failure("day of the week expected")
  ) named "dayOfWeek"
}