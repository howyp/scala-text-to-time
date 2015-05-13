package io.github.howyp.parser

import io.github.howyp.test.{SampleDates, ParsersSpec}

class NumberParsersSpec extends ParsersSpec with NumberParsers with SampleDates {
  ParserSpec(
    parser = positiveInteger,
    validExpressions = Map(
      "1" -> 1,
      "12345678" -> 12345678
    ),
    invalidExpressions = Map(
      "0" -> "positive integer expected",
      "-0" -> "positive integer expected",
      "-5" -> "positive integer expected"
    )
  )
}
