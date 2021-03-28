package uk.co.odinconsultants.dojo.algos.search

object MyLongestCommonSubsequence {

  def lcsRecursive[T: Ordering](xs: Seq[T], ys: Seq[T]): Seq[T] = {
    def lcs[T: Ordering](xs: Seq[T], ys: Seq[T], acc: Seq[T]): Seq[T] = {
      if (xs.size == 0 || ys.size == 0) {
        acc
      } else {
        val x = xs.head
        val y = ys.head
        if (x == y) {
          lcs(xs.tail, ys.tail, acc :+ x)
        } else {
          val a = lcs(xs.tail, ys, acc)
          val b = lcs(xs, ys.tail, acc)
          if (a.length > b.length) {
            a
          } else {
            b
          }
        }
      }
    }

    lcs(xs, ys, Seq.empty[T])
  }

  def lcsMemoization[T: Ordering](xs: Seq[T], ys: Seq[T]): Seq[T] = {
    val n: Int = xs.length
    val m: Int = ys.length
    type Element = Seq[T]
    val missing: Element = null
    val matrix: Array[Array[Element]] = for {
      _ <- (0 to n).toArray
    } yield (0 to m).map(_ => missing).toArray

    def calc(i: Int, j: Int, acc: Seq[T]): Seq[T] = {
      if (i == n || j == m) {
        acc
      } else {
        if (matrix(i)(j) != missing) {
          matrix(i)(j)
        } else if (xs(i) == ys(j)) {
          val newElement: Seq[T] = acc :+ xs(i)
          matrix(i)(j) = newElement
          calc(1 + 1, j + 1, newElement)
        } else {
          val a = calc(i + 1, j, acc)
          val b = calc(i, j + 1, acc)
          if (a.length > b.length) {
            matrix(i)(j) = a
            a
          } else {
            matrix(i)(j) = b
            b
          }
        }
      }
    }
    calc(0, 0, Seq.empty[T])
  }
}
