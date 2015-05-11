import io.github.howyp.TextToDate
import org.joda.time.LocalDate
import org.scalatest.matchers.{MatchResult, Matcher}
import org.scalatest.{Matchers, WordSpec}

class TextToDateSpec extends WordSpec with Matchers with TextToDate {
  val now = LocalDate.parse("2015-05-09")
  val nowPlusADay = LocalDate.parse("2015-05-10")
  val nowPlusFiveDays = LocalDate.parse("2015-05-14")
  val nowMinusADay = LocalDate.parse("2015-05-08")
  val nowMinusTwoDays = LocalDate.parse("2015-05-07")


  "TextToDate" should {
    Map(
      "now" -> now,
      "today" -> now,
      "tomorrow" -> nowPlusADay,
      "yesterday" -> nowMinusADay,
      "day before yesterday" -> nowMinusTwoDays,
      "day before today" -> nowMinusADay,
      "day after today" -> nowPlusADay,
      "5 days" -> nowPlusFiveDays,
      "2 days before tomorrow" -> nowMinusADay,
      "4 days after tomorrow" -> nowPlusFiveDays
    ) foreach { case (sample, expected) =>
      s"understand '$sample'" in {
        parseAll(relativeDay, sample) should succeedWith (expected)
      }
    }

    Map(
      "asdfghjkliuytf" -> "no relative date found"
    ) foreach { case (sample, expected) =>
      s"fail to parse '$sample'" in {
        parseAll(relativeDay, sample) should failWith (expected)
      }
    }
  }

  def succeedWith[T](expected: T)(implicit ev: Manifest[T]) = Matcher[ParseResult[T]] {
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
