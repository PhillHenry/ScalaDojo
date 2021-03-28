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
          if (a.length > b.length) a else b
        }
      }
    }

    lcs(xs, ys, Seq.empty[T])
  }

  def lcsLengthMemoization[T: Ordering](xs: Seq[T], ys: Seq[T]): Int = {
    val n: Int = xs.length
    val m: Int = ys.length
    type Element = Int
    val missing: Element = -1
    val matrix: Array[Array[Element]] = for {
      _ <- (0 to n).toArray
    } yield (0 to m).map(_ => missing).toArray

    def calc(i: Int, j: Int): Int = {
      val v: Element = matrix(i)(j)

      def isWithinRows: Boolean = i < n - 1

      def isWithinColumns: Boolean = j < m - 1

      if (v != missing) {
        v
      } else if (xs(i) == ys(j)) {
        val newVal: Element = 1 + (if (isWithinColumns & isWithinColumns) calc(i + 1, j + 1) else 0)
        matrix(i)(j) = newVal
        newVal
      } else {
        val a = if (isWithinRows) calc(i + 1, j) else 0
        val b = if (isWithinColumns) calc(i, j + 1) else 0
        val optimal = math.max(a, b)
        matrix(i)(j) = optimal
        optimal
      }
    }
    calc(0, 0)
    var max = missing
    for {
      i <- 0 to n
      j <- 0 to m
    } yield {
      max = math.max(matrix(i)(j), max)
      max
    }
    max
  }
}
