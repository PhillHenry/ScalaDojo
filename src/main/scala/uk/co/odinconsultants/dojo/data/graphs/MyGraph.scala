package uk.co.odinconsultants.dojo.data.graphs


object MyGraph {

  type ID = Int
  case class Edge(from: ID, to: ID)

  case class Vertex(id: ID, outgoing: Seq[Vertex])

  def buildGraphMapping(edges: Seq[Edge]): Map[ID, Seq[ID]] =
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

  def traverse(from: ID, to: ID): Option[Seq[Edge]] = ???

}
