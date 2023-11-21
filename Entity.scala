package tekstipeli

import scala.collection.mutable.Map

class Entity(name: String, description: String):

  private val neighbors = Map[String, Area]()

  def neighbor(direction: String) = this.neighbors.get(direction)

  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits

  def fullDescription: Unit =
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    this.description + exitList


end Entity
