package io.github.howyp

import org.joda.time.LocalDate

object TextToDate extends RelativeDateParser {
  def now = LocalDate.now
}
