package io.github.howyp

class NumberParsersSpec extends ParserSpec[Int] with NumberParsers with SampleDates {
  def parser = positiveInteger
  def validExpressions = Map(
    "1" -> 1,
    "12345678" -> 12345678
  )
  def invalidExpressions = Map(
    "0" -> "positive integer expected",
    "-0" -> "positive integer expected",
    "-5" -> "positive integer expected"
  )
}
