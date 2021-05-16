package uk.co.odinconsultants.dojo.data.tries

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class SeqNodeTest extends AnyWordSpec with Matchers {

  val words = Array("she", "sells", "sea", "shells", "on", "the", "seashore")

  type Element = Char
  type MyNode = SeqNode[Element]

  val root = SeqNode[Char](None, Map.empty)

  "Adding bigrams" should {
    def checkXY(xy: MyNode): Unit = xy.find("x") match {
      case None     => fail("Cannot find first element")
      case Some(x)  => x.find("y") match {
        case None     => fail("Cannot find second element")
        case Some(y)  => y.map shouldBe Map.empty
      }
    }

    "produce a tree" in {
      val xy = root.add("xy")
      val xyz = xy.add("xz")
      withClue(s"map:\n${xyz.map.mkString("\n")}\n") {
        xyz.map should have size 1
      }
      checkXY(xyz)
    }

    "produce a chain" in {
      val xy = root.add("xy")
      xy.map should have size 1
      checkXY(xy)
    }

  }

  def checkContains(node: MyNode, xs: Seq[Element]): MyNode = node.find(xs) match {
    case None     => fail(s"Could not find $xs")
    case Some(x)  => x
  }

  def addAndCheck(node: MyNode, xs: Seq[Element]): MyNode = {
    val newRoot = root.add(xs)
    checkContains(newRoot, xs)
    newRoot.parent shouldBe None
    newRoot
  }

  "Adding an empty String" should {
    "be a no-op" in {
      val x = root.add("")
      x.find("") match {
        case Some(x) => fail("trie cannot contain empty string")
        case None =>
      }
    }
  }

  def checkAllWordsAreIn(node: MyNode): Unit = words.foreach { word =>
    node.find(word) match {
      case Some(x) => println(s"Found $word in $x")
      case None => fail(s"Could not find $word in $node")
    }
  }

  def addAllWordsAndCheck(x: MyNode): MyNode = words.foldLeft(x) { case (newRoot, aWord) =>
      addAndCheck(newRoot, aWord)
    }

  "Adding words" should {
    "return a trie containing that word" ignore {
      val complete = addAllWordsAndCheck(root)
      checkAllWordsAreIn(complete)
    }
    "be idempotent" ignore {
      val first   = addAllWordsAndCheck(root)
      val second  = addAllWordsAndCheck(first)
      checkAllWordsAreIn(second)
    }
  }

  "Adding single letters" should {
    "create new nodes" in {
      val x = root.add("x")
      x.map should have size 1
      val y = x.add("y")
      y.map should have size 2
      val z = y.add("z")
      z.map should have size 3
    }
    "be idempotent" in {
      val x = root.add("x")
      x.map should have size 1
      val xx = root.add("x")
      xx.map should have size 1
    }
  }

}
