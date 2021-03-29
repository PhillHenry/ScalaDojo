package uk.co.odinconsultants.dojo.algos.graphs

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.algos.graphs.MyGraphs.emptyMatrix

class MyGraphsSpec extends AnyWordSpec with Matchers {

  "Empty matrix" should {
    val nRows = 7
    val nCols = 11
    val m = emptyMatrix(nRows, nCols)
    s"have size $nRows x $nCols" in {
      m should have length nRows
      for {
        row <- m
      } yield {
        row should have length nCols
      }
    }
    "have all elements equal to 0" in {
      for {
        row <- m
        x <- row
      } yield {
        x shouldBe 0d
      }
    }
  }

}
