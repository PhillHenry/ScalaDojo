package uk.co.odinconsultants.dojo.algos.sort

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.algos.sort.MyMergeSort.mergeSort

class MyMergeSortSpec extends AnyWordSpec with Matchers {

  "Empty collection" should {
    "return empty collection" in {
      mergeSort(Seq.empty[Int]) shouldBe Seq.empty
    }
  }

  "Already sorted collection" should {
    "return itself" in {
      val xs: Range.Inclusive = 1 to 10
      mergeSort(xs) shouldBe xs
    }
  }

  "Single element collection" should {
    "return itself" in {
      val xs = Seq(1)
      mergeSort(xs) shouldBe xs
    }
  }

  val xs: Range.Inclusive = (0 to 10)
  val shuffled = scala.util.Random.shuffle(xs.toList)

  s"${shuffled.mkString(", ")}" should {
    "be ordered" in {
      withClue(s"Supposed to order ${shuffled.mkString(", ")}\n") {
        mergeSort(shuffled) shouldBe xs
      }
    }
  }
}
