package tekstipeli

import scala.collection.mutable.Map

/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area):

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var currentInventory = Map[String, Item]()
  private var currentBattle: Option[Battle] = None

  def battle: Battle = this.currentBattle.get

  def isInBattle: Boolean = currentBattle.nonEmpty

  def startGhostBattle(enemy: Entity): Unit = this.currentBattle = Some(new GhostBattle(this, enemy))

  def startWolfBattle(enemy: Entity): Unit = this.currentBattle = Some(new WolfBattle(this, enemy))

  def endBattle(): Unit = this.currentBattle = None

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the player’s current location. */
  def location = this.currentLocation

  def help: String = s"Commands available:\ngo\nget\ninventory\nexamine\ndrop\nrest\nquit"

  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player’s current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String): String =
    val destination = this.location.neighbor(direction)
    if destination.isEmpty then
      "You can't go " + direction + "."
    else
      this.currentLocation = destination.getOrElse(this.currentLocation)
      if this.currentLocation.containsEntity then
        val entity: Entity = currentLocation.getEntity
        if !entity.isPacified then
          entity.name match
            case "Kloopstanaab" => this.startGhostBattle(entity)
            case "Lesser Wolf"  => this.startWolfBattle(entity)
          s"\n${entity.fullDescription} appears!"
        else "You go " + direction + "."
      else "You go " + direction + "."


  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest(): String = "You rest for a while. Better get a move on, though."


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit(): String =
    this.quitCommandGiven = true
    ""

  def get(itemName: String): String =
    var itemPick: String = ""
    if this.location.contains(itemName) then
      this.currentInventory += itemName -> this.location.removeItem(itemName).get
      itemPick = s"You pick up the $itemName."
    else itemPick = s"There is no $itemName here to pick up."
    itemPick


  def has(itemName: String): Boolean = this.currentInventory.contains(itemName)


  def inventory: String =
    var itemList: String = ""
    if this.currentInventory.nonEmpty then
      itemList = s"You are carrying:\n" + this.currentInventory.keys.mkString("\n")
    else itemList = "You are empty-handed."
    itemList


  def examine(itemName: String): String =
    var itemDesc: String = ""
    if this.currentInventory.contains(itemName) then
      itemDesc = s"You look closely at the $itemName.\n${currentInventory(itemName).description}"
    else itemDesc = "If you want to examine something, you need to pick it up first."
    itemDesc


  def drop(itemName: String): String =
    var itemDropped: String = ""
    if this.currentInventory.contains(itemName) then
        this.location.addItem(this.currentInventory(itemName))
        this.currentInventory.remove(itemName)
        itemDropped = s"You drop the $itemName."
    else itemDropped = "You don't have that!"
    itemDropped

  def discard(item: String): Unit = this.currentInventory.remove(item)


    /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player