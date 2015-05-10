# scala-text-to-time

Parse human-readable times and dates.

_Still in a very early stage of development, but here are some examples:_

```scala
scala> import io.github.howyp.TextToDate
import io.github.howyp.TextToDate

scala> import org.joda.time.LocalDate
import org.joda.time.LocalDate

scala> LocalDate.now
res0: org.joda.time.LocalDate = 2015-05-10

scala> TextToDate.parse("now")
res3: org.joda.time.LocalDate = 2015-05-10

scala> TextToDate.parse("tomorrow")
res4: org.joda.time.LocalDate = 2015-05-11

scala> TextToDate.parse("day before yesterday")
res5: org.joda.time.LocalDate = 2015-05-08
```
