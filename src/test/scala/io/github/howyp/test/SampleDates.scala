package io.github.howyp.test

import org.joda.time.LocalDate

trait SampleDates {
  def now = LocalDate.parse("2015-05-09")

  def nowPlusADay = LocalDate.parse("2015-05-10")
  def nowPlusTwoDays = LocalDate.parse("2015-05-11")
  def nowPlusThreeDays = LocalDate.parse("2015-05-12")
  def nowPlusFourDays = LocalDate.parse("2015-05-13")
  def nowPlusFiveDays = LocalDate.parse("2015-05-14")
  def nowPlusSixDays = LocalDate.parse("2015-05-15")
  def nowPlusSevenDays = LocalDate.parse("2015-05-16")

  def nowMinusADay = LocalDate.parse("2015-05-08")
  def nowMinusTwoDays = LocalDate.parse("2015-05-07")
}
