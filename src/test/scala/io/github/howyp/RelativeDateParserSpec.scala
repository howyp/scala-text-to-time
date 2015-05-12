package io.github.howyp

import org.joda.time.LocalDate

class RelativeDateParserSpec extends ParserSpec[LocalDate] with RelativeDateParser with SampleDates {

  def parser: Parser[LocalDate] = relativeDay

  def validExpressions = Map(
    "now" -> now,
    "today" -> now,
    "tomorrow" -> nowPlusADay,
    "yesterday" -> nowMinusADay,
    "day before yesterday" -> nowMinusTwoDays,
    "day before today" -> nowMinusADay,
    "day after today" -> nowPlusADay,
    "5 days" -> nowPlusFiveDays,
    "2 days before tomorrow" -> nowMinusADay,
    "4 days after tomorrow" -> nowPlusFiveDays
  )

  def invalidExpressions = Map(
    "asdfghjkliuytf" -> "no relative date found"
  )
}
