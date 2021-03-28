package uk.co.odinconsultants.dojo.algos.search

object MyLongestCommonSubsequence {

  def longestCommonSubsequence[T: Ordering](xs: Seq[T], ys: Seq[T]): Seq[T] = {
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

}
