package io.github.howyp

import org.scalatest.{Matchers, WordSpec}

import scala.util.parsing.combinator.RegexParsers

trait ParserSpec extends WordSpec with Matchers with ParserResultMatchers {
  this: RegexParsers =>

  def ParserSpec[Result](parser: this.Parser[Result], validExpressions: Map[String, Result], invalidExpressions: Map[String, String]) = {
    s"$parser" should {
      validExpressions foreach { case (sample, expected) =>
        s"successfully parse '$sample'" in {
          parseAll(parser, sample) should succeedWith(expected)
        }
      }
      invalidExpressions foreach { case (sample, expected) =>
        s"fail to parse '$sample'" in {
          parseAll(parser, sample) should failWith(expected)
        }
      }
    }
  }
}
