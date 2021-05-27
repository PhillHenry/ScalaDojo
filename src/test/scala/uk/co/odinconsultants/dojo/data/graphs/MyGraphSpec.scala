package uk.co.odinconsultants.dojo.data.graphs

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MyGraphSpec extends AnyWordSpec with Matchers {

  import MyGraph._

  val edges = Seq(Edge(0, 1), Edge(0, 6), Edge(0, 5), Edge(2, 0), Edge(2, 3), Edge(3, 5),
    Edge(5, 4), Edge(6, 4), Edge(6, 9), Edge(7, 6), Edge(8, 7), Edge(9, 11), Edge(9, 12),
    Edge(9, 10), Edge(11, 12))

  val allNodes = edges.foldLeft(Set.empty[ID]) { case (acc, x) =>
      acc ++ Set(x.from, x.to)
  }

  "Map created from edges" should {
    val g = buildGraphMapping(edges)
    "contain all vertices" in {
      allNodes.foreach { node =>
        g.keySet should contain (node)
      }
    }
    "contains all edges" in {
      edges.foreach { edge =>
        import edge._
        val adjacencyList = g(from)
        adjacencyList should contain(to)
      }
    }
  }

}
