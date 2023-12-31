package tekstipeli

import scala.collection.mutable.Map


class Area(var name: String, var description: String):

  private val neighbors = Map[String, Area]()
  private var entity: Option[Entity] = None
  private var items = Map[String, Item]()

  
  def neighbor(direction: String) = this.neighbors.get(direction)

  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits


  def containsEntity: Boolean = this.entity.isDefined

  def addEntity(entity: Entity) =
    this.entity = Some(entity)

  def getEntity: Entity = this.entity.get


  def fullDescription =
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    if items.nonEmpty then
      val itemList = "\n\nYou see here: " + this.items.keys.mkString(" ")
      this.description + itemList + exitList
    else this.description + exitList


  def addItem(item: Item): Unit = items += item.name -> item

  def contains(itemName: String): Boolean = items.contains(itemName)

  def removeItem(itemName: String): Option[Item] = items.remove(itemName)


end Area

