package uk.co.odinconsultants.dojo.algos.search

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.algos.search.MyBinarySearch.indexOf

class MyBinarySearchSpec extends AnyWordSpec with Matchers {

  "Empty collection" should {
    "return nothing" in {
      indexOf(Array.empty[Int], 1) shouldBe None
    }
  }

  "Collection missing value" should {
    "return nothing" in {
      indexOf(Array(2), 1) shouldBe None
    }
  }

  "Single valued array" should {
    "return 0" in {
      indexOf(Array(1), 1) shouldBe Some(0)
    }
  }

  "Values" should {
    val xs = (0 to 10).toArray
    s"be found in array ${xs.mkString(", ")}" in {
      checkPositionsForZeroBasedContiguous(xs)
    }
    "should be found even if array is unordered" in {
      checkPositionsForZeroBasedContiguous(scala.util.Random.shuffle(xs).toArray)
    }
  }

  private def checkPositionsForZeroBasedContiguous(xs: Array[Int]): Unit = {
    for {
      x <- xs
    } yield {
      print(s"Looking for $x in ${xs.mkString(", ")}")
      val actual: Option[Int] = indexOf(xs, x)
      actual.foreach(i => print(s"Found $x at $i"))
      actual shouldBe Some(x)
    }
  }
}
