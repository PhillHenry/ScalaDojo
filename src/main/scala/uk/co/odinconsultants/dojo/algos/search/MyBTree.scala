package uk.co.odinconsultants.dojo.algos.search

import scala.collection.Seq

case class Node[K: Ordering, V](children: Seq[(K, Node[K, V])]) {

  val sorted = children.sortBy(_._1)

  def lessThan(x: K, y: K): Boolean = Ordering[K].compare(x, y) <= 0

  def findIndexOf(k: K): Int =
    sorted.zipWithIndex
      .find { case ((k1, _), _) => lessThan(k, k1) }
      .map { case ((_, _), i) => i }
      .getOrElse(sorted.size)


}

class MyBTree[K: Ordered, V](m: Int, root: Node[K, V]) {

}
