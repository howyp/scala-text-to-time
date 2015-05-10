package io.github.howyp

import org.joda.time.LocalDate

import scala.util.parsing.combinator.RegexParsers

trait TextToDate extends RegexParsers {
  def now: LocalDate

  def integer: Parser[Int] = "\\d+".r ^^ (_.toInt)

  def today: Parser[LocalDate] = ("now" | "today") ^^^ now
  def tomorrow: Parser[LocalDate] = "tomorrow" ^^^ now.plusDays(1)
  def yesterday: Parser[LocalDate] = "yesterday" ^^^ now.minusDays(1)

  def daysBeforeAfter: Parser[Int] = opt(integer) ~ "days?".r ~ opt("before"| "after") map {
    case None        ~ _ ~ Some("before") => - 1
    case None        ~ _ ~ Some("after")  => + 1
    case Some(count) ~ _ ~ None           => + count
  }

  def relativeDay: Parser[LocalDate] = opt(daysBeforeAfter) ~ opt(today | tomorrow | yesterday) map {
    case None               ~ Some(date) => date
    case Some(dayIncrement) ~ Some(date) => date plusDays dayIncrement
    case Some(dayIncrement) ~ None       => now  plusDays dayIncrement
  }

  def parse(text: String): LocalDate = parse(relativeDay, text) match {
    case Success(r, _) => r
    case Failure(msg, _) => throw new RuntimeException(msg)
  }
}

object TextToDate extends TextToDate {
  def now = LocalDate.now
  def apply = parse _
}
