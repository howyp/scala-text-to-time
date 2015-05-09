import io.github.howyp.TextToDate
import org.joda.time.LocalDate
import org.scalatest.{Matchers, WordSpec}

class TextToDateSpec extends WordSpec with Matchers {
  val exampleNow = LocalDate.parse("2015-05-09")
  val exampleTomorrow = LocalDate.parse("2015-05-10")
  val parse = new TextToDate { val now = exampleNow }.parse _

  "TextToDate" should {
    "understand 'now'" in {
      parse("now") should be (exampleNow)
    }
    "understand 'tomorrow'" in {
      parse("tomorrow") should be (exampleTomorrow)
    }
  }
}
