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

  val g = buildGraphMapping(edges)

  "A jumbled path" should {
    "be more direct" in {
      val jumbled = List(0, 1, 6, 5, 4, 9, 11, 12)
      withClue(s"$jumbled\n") {
        clean(g, jumbled) shouldBe List(0, 6, 9, 12)
      }
    }
  }

  "Map created from edges" should {
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

  "Traversing a map" should {
    "yield the path when possible" in {
      val path = traverse(0, 12, g)
      path shouldBe Seq(0, 6, 9, 12)
    }
    "should return None if not possible" in {
      traverse(12, 0, g) shouldBe Seq.empty[ID]
    }
  }

  "An empty graph" should {
    "transform to a matrix of all zeros" in {
      val m = toMatrix(buildGraphMapping(Seq.empty))
      for {
        row <-m
      } yield {
        row.filter(_ != 0d) shouldBe empty
      }
    }
  }

  "A non empty graph" should {
    val m = toMatrix(g)
    val numNodes = 13
    "be transformed to a matrix the size of the keys" in {
      m.length shouldBe numNodes
      for {
        row <- m
      } yield {
        row.length shouldBe numNodes
      }
    }
  }

}
