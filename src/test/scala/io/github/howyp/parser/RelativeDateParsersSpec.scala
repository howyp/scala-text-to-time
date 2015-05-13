package io.github.howyp.parser

import io.github.howyp.test.{SampleDates, ParsersSpec}

class RelativeDateParsersSpec extends ParsersSpec with RelativeDateParser with SampleDates {
  val namedDaySpec = ParserSpec(
    parser = namedDay,
    validExpressions = Map(
      "now" -> now,
      "today" -> now,
      "tomorrow" -> nowPlusADay,
      "yesterday" -> nowMinusADay
    ),
    invalidExpressions = Map(
      "asdfghjkliuytf" -> "named day expected"
    )
  )
  ParserSpec(
    parser = relativeDay,
    validExpressions = namedDaySpec.validExpressions ++ Map(
      "day before yesterday" -> nowMinusTwoDays,
      "day before today" -> nowMinusADay,
      "day after today" -> nowPlusADay,
      "5 days" -> nowPlusFiveDays,
      "2 days before tomorrow" -> nowMinusADay,
      "4 days after tomorrow" -> nowPlusFiveDays
    ),
    invalidExpressions = Map(
      "asdfghjkliuytf" -> "no relative date found"
    )
  )
}
