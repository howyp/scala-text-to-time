import io.github.howyp.TextToDate
import org.joda.time.LocalDate
import org.scalatest.{Matchers, WordSpec}

class TextToDateSpec extends WordSpec with Matchers {
  val exampleNow = LocalDate.parse("2015-05-09")
  val examplePlusADay = LocalDate.parse("2015-05-10")
  val examplePlusFiveDays = LocalDate.parse("2015-05-14")
  val exampleMinusADay = LocalDate.parse("2015-05-08")
  val exampleMinusTwoDays = LocalDate.parse("2015-05-07")
  val parse = new TextToDate { val now = exampleNow }.parse _

  "TextToDate" should {

    val validExpressions = Map(
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
    )

    for ((phrase, expected) <- validExpressions) {
      s"understand '$phrase'" in {
        parse(phrase) should be (expected)
      }
    }
  }
}
