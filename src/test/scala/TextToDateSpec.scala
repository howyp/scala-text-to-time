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
    "understand words for the current day" in {
      parse("now") should be (exampleNow)
      parse("today") should be (exampleNow)
    }
    "understand 'tomorrow'" in {
      parse("tomorrow") should be (examplePlusADay)
    }
    "understand 'yesterday'" in {
      parse("yesterday") should be (exampleMinusADay)
    }
    "understand 'day before yesterday'" in {
      parse("day before yesterday") should be (exampleMinusTwoDays)
    }
    "understand 'day before today'" in {
      parse("day before today") should be (exampleMinusADay)
    }
    "understand 'day after today'" in {
      parse("day after today") should be (examplePlusADay)
    }
    "understand '5 days'" in {
      parse("5 days") should be (examplePlusFiveDays)
    }
  }
}
