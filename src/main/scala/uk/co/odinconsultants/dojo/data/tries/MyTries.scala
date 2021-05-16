package uk.co.odinconsultants.dojo.data.tries

import scala.collection.immutable.Map

case class SeqNode[T](parent: Option[SeqNode[T]], map: Map[T, SeqNode[T]]) {

  def find(ts: Seq[T]): Option[SeqNode[T]] = {
    if (ts.isEmpty)
      None
    else
      map.get(ts.head).flatMap { node =>
        val rest = ts.tail
        if (rest.isEmpty) {
          Some(node)
        } else {
          node.find(rest)
        }
      }
  }

  def add(ts: Seq[T]): SeqNode[T] = find(ts) match {
    case Some(_)  => this
    case None     => modify(this, ts)
  }

  def modify(parent: SeqNode[T], ts: Seq[T]): SeqNode[T] =
    if (ts.length == 0) {
      this
    } else if (ts.length == 1) {
      val child: SeqNode[T] = SeqNode(Some(parent), Map.empty)
      parent.copy(map = parent.map + (ts.head -> child))
    } else {
      val k = ts.head
      parent.map.get(k) match {
        case Some(child)  => parent.copy(map = parent.map + (k -> modify(child, ts.tail)))
        case None         =>
          val value: SeqNode[T] = SeqNode(Some(parent), Map.empty)
          val child = modify(value, ts.tail)
          parent.copy(map = parent.map + (k -> child))
      }
    }

}

object MyTries {

  type StringNode = SeqNode[Char]

}
