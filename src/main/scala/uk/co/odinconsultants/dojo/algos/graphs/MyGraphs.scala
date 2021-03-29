package uk.co.odinconsultants.dojo.algos.graphs

object MyGraphs {

  type Matrix = Array[Array[Double]]

  def multiply(xMatrix: Matrix, yMatrix: Matrix): Matrix = {
    val xCols = xMatrix(0).length
    val xRows = xMatrix.length
    val yCols = yMatrix(0).length
    val yRows = yMatrix.length

    assert(xCols == yRows)

    val toReturn = matrix(xRows, yCols, 0)
    for {
      i <- 0 until xRows
      j <- 0 until yCols
    } yield {
      val xs = xMatrix(i)
      val ys = for {
        k <- 0 until yRows
      } yield {
        yMatrix(k)(j)
      }
      val multiplied = xs.zip(ys).map { case (x, y) => x * y }
      toReturn(i)(j) = multiplied.sum
    }
    toReturn
  }

  def matrix(nRows: Int, nCols: Int, initial: Double = 0d): Matrix = {
    val xs = for {
      _ <- 1 to nRows
    } yield Array.fill(nCols)(initial)
    xs.toArray
  }


}
