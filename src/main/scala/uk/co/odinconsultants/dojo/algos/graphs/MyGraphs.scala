package uk.co.odinconsultants.dojo.algos.graphs

object MyGraphs {

  type Matrix = Array[Array[Double]]

  def emptyMatrix(nRows: Int, nCols: Int): Matrix = {
    val xs = for {
      _ <- 1 to nRows
    } yield Array.fill(nCols)(0d)
    xs.toArray
  }


}
