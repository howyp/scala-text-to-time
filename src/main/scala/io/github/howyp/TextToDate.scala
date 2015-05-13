package io.github.howyp

import io.github.howyp.parser.RelativeDateParser
import org.joda.time.LocalDate

object TextToDate extends RelativeDateParser {
  def now = LocalDate.now
}
