package io.github.howyp

import org.scalatest.{Matchers, WordSpec}

import scala.util.parsing.combinator.RegexParsers

trait ParserSpec[T] extends WordSpec with Matchers with ParserResultMatchers {
  this: RegexParsers =>

  def parser: this.Parser[T]

  def validExpressions: Map[String, T]

  def invalidExpressions: Map[String, String]

  s"$parser" should {
    validExpressions foreach { case (sample, expected) =>
      s"understand '$sample'" in {
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
