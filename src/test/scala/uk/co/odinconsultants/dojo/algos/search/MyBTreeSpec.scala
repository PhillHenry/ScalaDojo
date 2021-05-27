package uk.co.odinconsultants.dojo.algos.search

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.Seq

class MyBTreeSpec extends AnyWordSpec with Matchers {

  type K = String
  type V = Int
  type KV = (K, Node[K, V])

  val emptyChildren = Seq.empty[KV]
  val emptyNode = Node(emptyChildren)

  trait PopulatedNode {
    val keyA = "a"
    val keyB = "b"
    val keyC = "c"
    val keyD = "d"
    val keyE = "e"
    val keyF = "f"
    val keyG = "g"
    val a: KV = (keyA, emptyNode)
    val b: KV = (keyB, emptyNode)
    val c: KV = (keyC, emptyNode)
    val d: KV = (keyD, emptyNode)
    val e: KV = (keyE, emptyNode)
    val f: KV = (keyF, emptyNode)
    val g: KV = (keyG, emptyNode)
  }

  "Searching" should {
    "find the right index" in new PopulatedNode {
      val toTest = Node(Seq(a, c, e))
      toTest.findIndexOf(keyA) shouldBe 0
      toTest.findIndexOf(keyC) shouldBe 1
      toTest.findIndexOf(keyE) shouldBe 2
    }
    "find the gap" in new PopulatedNode {
      val toTest = Node(Seq(b, d, f))
      toTest.findIndexOf(keyA) shouldBe 0
      toTest.findIndexOf(keyG) shouldBe 3
    }
  }
}
