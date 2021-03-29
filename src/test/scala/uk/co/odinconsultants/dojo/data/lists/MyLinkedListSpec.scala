package uk.co.odinconsultants.dojo.data.lists

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.data.lists.MyLinkedList.makeMyLinkedList

import scala.collection.mutable.ArrayBuffer

class MyLinkedListSpec extends AnyWordSpec with Matchers {

  val expected = Array(1,2,3,5,7,11)

  s"Linked list of ${expected.mkString(", ")}" should {
    s"have ${expected.length} elements" in {
      val linked = makeMyLinkedList(expected)
      val actual = new ArrayBuffer[Int]()
      actual.append(linked.t)
      var i = 1
      var next = linked.next
      while (next.isDefined) {
        i = i + 1
        val element = next.get
        actual.append(element.t)
        next = element.next
      }
      i shouldBe expected.length
      withClue(linked) {
        actual.toArray shouldBe expected
      }
    }
  }

}
