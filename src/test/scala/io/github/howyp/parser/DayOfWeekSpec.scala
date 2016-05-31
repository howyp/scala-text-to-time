package io.github.howyp.parser

import org.scalatest.{Matchers, WordSpec}

class DayOfWeekSpec extends WordSpec with Matchers {
  import DayOfWeek._
  "DayOfWeek" should {
    "be able to calculate the number of days between two named days" in {
      daysBetween(Monday, Tuesday) should be (1)
      daysBetween(Monday, Wednesday) should be (2)
      daysBetween(Tuesday, Friday) should be (3)
      daysBetween(Sunday, Saturday) should be (6)
      daysBetween(Sunday, Sunday) should be (7)
    }
  }
}
