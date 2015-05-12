package io.github.howyp

import scala.util.parsing.combinator.RegexParsers

trait NumberParsers extends RegexParsers {
  def positiveInteger: Parser[Int] = (
    "\\d+".r ^^ (_.toInt)
    filter (_ > 0)
    withFailureMessage "positive integer expected"
    named "positiveInteger"
  )
}
