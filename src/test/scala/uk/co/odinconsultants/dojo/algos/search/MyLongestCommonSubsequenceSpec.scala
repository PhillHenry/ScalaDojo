package uk.co.odinconsultants.dojo.algos.search

import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.algos.search.MyLongestCommonSubsequence.{lcsLengthMemoization, lcsRecursive}

class MyLongestCommonSubsequenceSpec extends AnyWordSpec with Matchers {

  val xs = "nematode knowledge"
  val ys = "empty bottle"
  val actual = "emt ole"

  def checkMemoization[T: Ordering](xs: Seq[T], ys: Seq[T], actual: Seq[T]): Assertion =
    lcsLengthMemoization(xs, ys) shouldBe actual.length

  def checkRecursive[T: Ordering](xs: Seq[T], ys: Seq[T], actual: Seq[T]): Assertion =
    lcsRecursive(xs, ys) shouldBe actual.toList

  s"Longest common subsequence using memoization" should {
    s"be $actual when comparing $xs and $ys" in {
      checkMemoization(xs, ys, actual)
    }

    "be itself when comparing to itself" ignore {
      checkMemoization(xs, xs, xs)
    }
  }

  s"Longest common subsequence using recursion" should {
    s"be $actual when comparing $xs and $ys" in {
      checkRecursive(xs, ys, actual)
    }

    "be itself when comparing to itself" in {
      checkRecursive(xs, xs, xs)
    }
  }
}
