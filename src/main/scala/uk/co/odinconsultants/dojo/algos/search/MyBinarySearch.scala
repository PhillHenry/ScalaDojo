package uk.co.odinconsultants.dojo.algos.search

object MyBinarySearch {

  def indexOf[T: Ordering](xs: Array[T], x: T): Option[Int] = {
    val ys = xs.sorted

    def binarySearch(xs: Array[T]): Option[Int] = {
      if (xs.isEmpty) {
        None
      } else if (xs.size == 1) {
        val actual = xs(0)
        if (actual == x)
          Some(0)
        else
          None
      } else if (xs.size == 2) {
        if (xs(0) == x) {
          Some(0)
        } else if (xs(1) == x) {
          Some(1)
        } else {
          None
        }
      } else {
        val pivot = xs.length / 2
        val a = binarySearch(xs.slice(0, pivot))
        val b = binarySearch(xs.slice(pivot, xs.length))
        if (a.isDefined) {
          a
        } else {
          b.map(_ + pivot)
        }
      }
    }

    binarySearch(xs.sorted)
  }

}
