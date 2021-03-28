package uk.co.odinconsultants.dojo.algos.sort

import scala.annotation.tailrec

object MyMergeSort {

  def isAscending[T: Ordering](a: T, b: T): Boolean = Ordering[T].lt(a, b)

  def mergeSort[T: Ordering](xs: Seq[T]): Seq[T] = {

    @tailrec
    def merge[T: Ordering](xs: Seq[T], ys: Seq[T], acc: Seq[T]): Seq[T] = {
      if (xs.isEmpty) {
        acc ++ ys
      } else if (ys.isEmpty) {
        acc ++ xs
      } else {
        val x: T = xs.head
        val y: T = ys.head
        if (isAscending(x, y)) {
          merge(xs.tail, ys, acc :+ x)
        } else {
          merge(xs, ys.tail, acc :+ y)
        }
      }
    }

    def sort[T: Ordering](xs: Seq[T]): Seq[T] = {
      val length: Int = xs.length
      if (length == 0 || length == 1) {
        xs
      } else if (length == 2) {
        val a: T = xs(0)
        val b: T = xs(1)
        if (!isAscending(a, b)) {
          Seq(b, a)
        } else {
          xs
        }
      } else {
        val pivot = length / 2
        val a = xs.slice(0, pivot)
        val b = xs.slice(pivot, length)
        val sortedA: Seq[T] = sort(a)
        val sortedB: Seq[T] = sort(b)
        merge(sortedA, sortedB, Seq.empty[T])
      }
    }

    sort(xs)
  }

}
