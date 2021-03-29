package uk.co.odinconsultants.dojo.data.lists

import scala.annotation.tailrec

object MyLinkedList {

  case class Element[T](t: T, next: Option[Element[T]])

  def makeMyLinkedList[T](xs: Array[T]): Element[T] = {
    @tailrec
    def addElement(xs: Array[T], acc: Element[T]): Element[T] = {
      if (xs.isEmpty) {
        acc
      } else {
        addElement(xs.tail, Element(xs.head, Some(acc)))
      }
    }
    val reversed = xs.reverse
    val end = Element(reversed.head, None)
    addElement(reversed.tail, end)
  }

}
