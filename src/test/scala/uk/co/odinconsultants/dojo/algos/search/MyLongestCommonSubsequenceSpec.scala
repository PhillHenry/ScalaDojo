package uk.co.odinconsultants.dojo.algos.search

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.algos.search.MyLongestCommonSubsequence.longestCommonSubsequence

class MyLongestCommonSubsequenceSpec extends AnyWordSpec with Matchers {

  val xs = "nematode knowledge"
  val ys = "empty bottle"
  val actual = "emt ole"

  s"Longest common subsequence of $xs and $ys" should {
    s"be $actual" in {
      longestCommonSubsequence(xs, ys) shouldBe actual.toList
    }
  }

  "LCS with self" should {
    "be self" in {
      longestCommonSubsequence(xs, xs) shouldBe xs.toList
    }
  }
}
