package io.github.howyp.parser

import org.joda.time.{LocalDate, DateTime}
import org.joda.time.DateTimeConstants._

object DayOfWeek {
  case object Monday extends DayOfWeek
  case object Tuesday extends DayOfWeek
  case object Wednesday extends DayOfWeek
  case object Thursday extends DayOfWeek
  case object Friday extends DayOfWeek
  case object Saturday extends DayOfWeek
  case object Sunday extends DayOfWeek

  def today = LocalDate.now.dayOfWeek().get match {
    case MONDAY => Monday
    case TUESDAY => Tuesday
    case WEDNESDAY => Wednesday
    case THURSDAY => Thursday
    case FRIDAY => Friday
    case SATURDAY => Saturday
    case SUNDAY => Sunday
  }

  def daysBetween(firstDay: DayOfWeek, secondDay: DayOfWeek) =
    values.indexOf(secondDay) - values.indexOf(firstDay) match {
      case pos if pos > 0 => pos
      case neg if neg <= 0 => values.size + neg
    }

  val values = Seq(
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday
  )
}

sealed trait DayOfWeek {
  val name = toString
}