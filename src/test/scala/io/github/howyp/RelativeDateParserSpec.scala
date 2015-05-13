package io.github.howyp

class RelativeDateParserSpec extends ParserSpec with RelativeDateParser with SampleDates {
  ParserSpec(
    parser = relativeDay,
    validExpressions = Map(
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
    ),
    invalidExpressions = Map(
      "asdfghjkliuytf" -> "no relative date found"
    )
  )
}
