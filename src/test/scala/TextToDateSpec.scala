import io.github.howyp.TextToDate
import org.joda.time.LocalDate
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Matchers, WordSpec}

class TextToDateSpec extends WordSpec with Matchers with TextToDate {
  val exampleNow = LocalDate.parse("2015-05-09")
  val examplePlusADay = LocalDate.parse("2015-05-10")
  val examplePlusFiveDays = LocalDate.parse("2015-05-14")
  val exampleMinusADay = LocalDate.parse("2015-05-08")
  val exampleMinusTwoDays = LocalDate.parse("2015-05-07")

  val now = exampleNow

  "TextToDate" should {
    Map(
      "now" -> exampleNow,
      "today" -> exampleNow,
      "tomorrow" -> examplePlusADay,
      "yesterday" -> exampleMinusADay,
      "day before yesterday" -> exampleMinusTwoDays,
      "day before today" -> exampleMinusADay,
      "day after today" -> examplePlusADay,
      "5 days" -> examplePlusFiveDays,
      "2 days before tomorrow" -> exampleMinusADay,
      "4 days after tomorrow" -> examplePlusFiveDays
    ) foreach { case (sample, expected) =>
      s"understand '$sample'" in {
        parseAll(relativeDay, sample) should succeedWith (expected)
      }
    }
  }

  def succeedWith[T](expected: T)(implicit ev: Manifest[T]) = Matcher[ParseResult[T]] {
    case Success(result, _) => MatchResult(
      matches = result == expected,
      rawFailureMessage = s"Result was '$result', expected '$expected'",
      rawNegatedFailureMessage = ""
    )
  }
}
