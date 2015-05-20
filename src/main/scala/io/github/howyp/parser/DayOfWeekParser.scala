package io.github.howyp.parser

import scala.util.parsing.combinator.RegexParsers

object DayOfWeekParser extends DayOfWeekParser

trait DayOfWeekParser extends RegexParsers {
  import DayOfWeek._

  def dayOfWeek: Parser[DayOfWeek] = (
    values.tail.foldLeft(parserFor(values.head)) { (p, d) =>
      p | parserFor(d)
    } | failure("day of the week expected")
  ) named "dayOfWeek"

  private def parserFor(d: DayOfWeek) =
    (d.name | d.name.toLowerCase) ^^^ d
}