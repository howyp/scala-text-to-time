package io.github.howyp.parser

import io.github.howyp.test.{SampleDates, ParsersSpec}

class RelativeDateParsersSpec extends ParsersSpec with RelativeDateParser with SampleDates {
  val namedDaySpec = ParserSpec(
    parser = `today/tomorrow/yesterday/___day`,
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
    parser = dayOfWeekFromNow,
    validExpressions = Map(
      "sunday" -> nowPlusADay,
      "monday" -> nowPlusTwoDays,
      "tuesday" -> nowPlusThreeDays,
      "wednesday" -> nowPlusFourDays,
      "thursday" -> nowPlusFiveDays,
      "friday" -> nowPlusSixDays,
      "saturday" -> nowPlusSevenDays
    ),
    invalidExpressions = Map(
      "asdfghjkliuytf" -> "day of the week expected"
    )
  )
  ParserSpec(
    parser = `x day(s)`,
    validExpressions = Map(
      "1 day" -> 1,
      "2 days" -> 2,
      "5 days" -> 5
    ),
    invalidExpressions = Map(
      "1 days" -> "incorrect mix of plural and singular",
      "2 day" -> "incorrect mix of plural and singular",
      "alksjdhflakjshdlf" -> "expected a number of days"
    )
  )
  ParserSpec(
    parser = relativeDay,
    validExpressions = namedDaySpec.validExpressions ++ Map(
      "day before yesterday" -> nowMinusTwoDays,
      "day before today" -> nowMinusADay,
      "day after today" -> nowPlusADay,
      "monday" -> nowPlusTwoDays,
      "day after monday" -> nowPlusThreeDays,
      "5 days" -> nowPlusFiveDays,
      "2 days before tomorrow" -> nowMinusADay,
      "4 days after tomorrow" -> nowPlusFiveDays,
      "4 days before thu" -> nowPlusADay
    ),
    invalidExpressions = Map(
      "asdfghjkliuytf" -> "no relative date found"
    )
  )
}
