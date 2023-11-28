package tekstipeli

class Adventure:

  val title = "T-talo dungeon crawl"

  // kartan määrittely:
  private val outside     = Area("Outside of the RUINS", "It's raining")
  private val entrance    = Area("Entrance Hall", "First room of the RUINS")
  private val middle      = Area("Main Hall", "The shadow of the ruins looms above, filling you with DETERMINATION.")
  private val north       = Area("Northern Hall", "The walls are filled runes of with long-forgotten tales.")
  private val south       = Area("Southern Hall", "The hall is partly collapsed.")
  private val hallway     = Area("Hallway", "Water is dripping from the mossy ceiling.")
  private val home        = Area("Home", "You've arrived home")
  private val destination = home

  outside     .setNeighbors(Vector("east" -> entrance))
  entrance    .setNeighbors(Vector("west" -> outside, "east" -> middle))
  middle      .setNeighbors(Vector("north" -> north, "south" -> south, "east" -> hallway, "west" -> entrance))
  hallway     .setNeighbors(Vector("west" -> middle, "east" -> destination))
  north       .setNeighbors(Vector("south" -> middle))
  south       .setNeighbors(Vector("north" -> middle))
  destination .setNeighbors(Vector("west" -> middle))

  hallway     .addEntity(new Entity("Kloopstanaab", "the melancholic ghost", 100))

  south       .addItem(new Item("sword", "something every self-respecting adventurer should have"))
  outside     .addItem(new Item("treat", "mmmm..., this looks tasty"))

 //  ("BOOOOOO!", "a melancholic ghost monster appears")

  val player = Player(outside) // aloitussijainti

  var turnCount = 0 // kuinka monta vuoroa kulunut

  val timeLimit = 40  // vuorojen max. ennen autom. häviötä


  def isComplete = this.player.location == this.destination

  def isOver = this.isComplete  || this.player.hasQuit || this.turnCount == this.timeLimit
  
  def welcomeMessage = "Welcome to the Dungeons beneath T-talo!"

  def goodbyeMessage =
    if this.isComplete then
      "GG WP"
    else if this.turnCount == this.timeLimit then // game over due to time limit
      "prrrr, time is out"
    else  // game over due to player quitting
      "hahaa loser"

  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport =
      if this.player.isInBattle then
        action.fight(this.player)
      else action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure
