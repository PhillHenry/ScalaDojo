package uk.co.odinconsultants.dojo.data.graphs


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

    def recurse(id: ID, acc: Path, alreadySeen: Set[ID]): Path = {
      if (alreadySeen.contains(id)) {
        Seq.empty
      } else if (id == to) {
        acc
      } else {
        val outgoing = g(id)
        outgoing.foldLeft(Seq.empty[ID]) { case (acc, x) =>
          acc ++ recurse(x, id +: acc, alreadySeen + id)
        }
      }
    }
    recurse(from, Seq.empty[ID], Set.empty[ID])
  }

}
