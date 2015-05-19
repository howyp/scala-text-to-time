package io.github.howyp.test

import org.scalatest.{Matchers, WordSpec}

import scala.util.parsing.combinator.RegexParsers

trait ParsersSpec extends WordSpec with Matchers with ParserResultMatchers {
  spec: RegexParsers =>

  case class ParserSpec[Result](parser: spec.Parser[Result], validExpressions: Map[String, Result], invalidExpressions: Map[String, String] = Map()) {
    s"$parser" should {

      "have a defined name" in {
        parser.toString() should not be "Parser (|)"
      }

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
