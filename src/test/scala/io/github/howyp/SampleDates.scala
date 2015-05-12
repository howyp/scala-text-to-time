package io.github.howyp

import org.joda.time.LocalDate

trait SampleDates {
  def now = LocalDate.parse("2015-05-09")
  def nowPlusADay = LocalDate.parse("2015-05-10")
  def nowPlusFiveDays = LocalDate.parse("2015-05-14")
  def nowMinusADay = LocalDate.parse("2015-05-08")
  def nowMinusTwoDays = LocalDate.parse("2015-05-07")
}
