package uk.co.odinconsultants.dojo.data.lists

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.co.odinconsultants.dojo.data.lists.MyLinkedList.makeMyLinkedList

class MyLinkedListSpec extends AnyWordSpec with Matchers {

  val xs = Array(1,2,3,5,7,11)

  s"Linked list of ${xs.mkString(", ")}" should {
    s"have ${xs.length} elements" in {
      val linked = makeMyLinkedList(xs)
      var i = 1
      var next = linked.next
      while (next.isDefined) {
        i = i + 1
        next = next.get.next
      }
      i shouldBe xs.length
    }
  }

}
