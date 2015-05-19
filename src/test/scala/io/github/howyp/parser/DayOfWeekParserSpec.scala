package io.github.howyp.parser

import io.github.howyp.test.ParsersSpec

class DayOfWeekParserSpec extends ParsersSpec with DayOfWeekParser {
  import DayOfWeek._

  ParserSpec(
    parser = dayOfWeek,
    validExpressions = Map(
      "Monday" -> Monday,
      "Tuesday" -> Tuesday,
      "Wednesday" -> Wednesday,
      "Thursday" -> Thursday,
      "Friday" -> Friday,
      "Saturday" -> Saturday,
      "Sunday" -> Sunday,
      "monday" -> Monday,
      "tuesday" -> Tuesday,
      "wednesday" -> Wednesday,
      "thursday" -> Thursday,
      "friday" -> Friday,
      "saturday" -> Saturday,
      "sunday" -> Sunday
    )
  )
}
