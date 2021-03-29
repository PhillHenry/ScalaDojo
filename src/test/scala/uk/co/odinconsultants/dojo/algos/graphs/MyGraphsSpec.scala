package uk.co.odinconsultants.dojo.algos.graphs

import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.algos.graphs.MyGraphs._

class MyGraphsSpec extends AnyWordSpec with Matchers {

  "Matrix multiplication" should {
    val xRows = 7
    val yRows = 11
    val yCols = 13
    val element = 1d
    val xMatrix = matrix(xRows, yRows, element)
    val yMatrix = matrix(yRows, yCols, element)
    val m = multiply(xMatrix, yMatrix)
    "yield the correct shape" in {
      checkMatrixShape(xRows, yCols, m)
    }
    "yield uniform elements" in {
      checkMatrixContainsOnly(m, yRows * element)
    }
  }

  "Empty matrix" should {
    val nRows = 7
    val nCols = 11
    val m = matrix(nRows, nCols)
    s"have size $nRows x $nCols" in {
      checkMatrixShape(nRows, nCols, m)
    }
    "have all elements equal to 0" in {
      checkMatrixContainsOnly(m)
    }
  }

  private def checkMatrixShape(nRows: Int, nCols: Int, m: Matrix): Unit = {
    m should have length nRows
    for {
      row <- m
    } yield {
      row should have length nCols
    }
  }

  private def checkMatrixContainsOnly(m: Matrix, element: Double = 0): Unit = {
    for {
      row <- m
      x <- row
    } yield {
      x shouldBe element
    }
  }
}
