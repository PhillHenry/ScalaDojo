package uk.co.odinconsultants.dojo.data.graphs

import scala.annotation.tailrec


object MyGraph {

  type ID = Int
  case class Edge(from: ID, to: ID)

  type Graph = Map[ID, Seq[ID]]

  def buildGraphMapping(edges: Seq[Edge]): Graph =
    edges.foldLeft(Map.empty[ID, Seq[ID]]) { case (acc, edge) =>
      import edge._
      val oldOutgoing = acc.getOrElse(from, Seq.empty[ID])
      val newOutgoing = oldOutgoing :+ to
      val newMappings = acc + (from -> newOutgoing)
      if (acc.keySet.contains(to)) {
        newMappings
      } else {
        newMappings + (to -> Seq.empty[ID])
      }
    }

  type Path = Seq[ID]
  def traverse(from: ID, to: ID, g: Graph): Path = {
    @tailrec
    def recurse(toVisit: Seq[ID], path: Path, alreadySeen: Set[ID]): Path = {
      if (toVisit.isEmpty) {
        Seq.empty
      } else {
        val id = toVisit.head
        val rest = toVisit.tail
        if (alreadySeen.contains(id)) {
          recurse(rest, path, alreadySeen)
        } else if (id == to) {
          path :+ id
        } else {
          val outgoing = g(id).toSet
          val viable = outgoing -- alreadySeen
          val newToVisit: Seq[ID] = rest ++ viable
          recurse(newToVisit, path :+ id, alreadySeen + id)
        }
      }
    }
    val aPath = recurse(Seq(from), Seq.empty[ID], Set.empty[ID])
    clean(g, aPath)
  }

  def clean(g: Graph, aPath: Seq[ID]): Seq[ID] = {

    def recurse(toVisit: Path, path: Path): Path = {
      if (toVisit.isEmpty) {
        path
      } else {
        val me = toVisit.head
        val rest = toVisit.tail
        rest.zipWithIndex.filter { case (x, _) => g(x).contains(me) }.lastOption match {
          case Some((x, i)) =>
            recurse(rest.drop(i), path :+ x)
          case None =>
            path
        }
      }
    }
    aPath.lastOption match {
      case None => Seq.empty
      case Some(last) => recurse(aPath.reverse, Seq(last)).reverse
    }
  }

}
