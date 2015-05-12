package io.github.howyp

import org.scalatest.matchers.{MatchResult, Matcher}

import scala.util.parsing.combinator.Parsers

trait ParserResultMatchers {
  this: Parsers =>

  def succeedWith[T](expected: T) = new Matcher[ParseResult[T]] {
    def apply(left: ParseResult[T]) = left match {
      case Success(result, _) => MatchResult(
        matches = result == expected,
        rawFailureMessage = s"Result was '$result', expected '$expected'",
        rawNegatedFailureMessage = s"Result should not have been $result"
      )
      case f => MatchResult(
        matches = false,
        rawFailureMessage = s"Parser returned '$f', expected '$expected'",
        rawNegatedFailureMessage = s"Parser returned '$f', result should not have been $expected"
      )
    }
  }

  def failWith(expected: String) = Matcher[ParseResult[_]] {
    case Failure(msg, _) => MatchResult(
      matches = msg contains expected,
      rawFailureMessage = s"Parse failure '$msg' did not contain '$expected'",
      rawNegatedFailureMessage = s"Parse failure '$msg' contained '$expected'"
    )
    case f => MatchResult(
      matches = false,
      rawFailureMessage = s"Parser returned '$f', expected '$expected'",
      rawNegatedFailureMessage = s"Parser returned '$f', result should not have been $expected"
    )
  }
}
