package tekstipeli

class Adventure:

  val title = "Teemu teekkari vs. kelju kylteri"

  // kartan määrittely:
  private val outside     = Area("Outside of the dungeon", "It's raining")
  private val middle      = Area("Middle", "Middle of the Dungeon")
  private val south       = Area("South", "Southern Dungeon")
  private val ghost       = Entity("BOOOOOO!", "a melancholic ghost monster appears")
  private val home        = Area("Home", "You've arrived home")
  private val destination = home

  middle      .setNeighbors(Vector("south" -> south, "east" -> ghost, "west" -> outside))
  outside     .setNeighbors(Vector("east" -> middle))
  ghost       .setNeighbors(Vector("west" -> middle, "east" -> destination))
  south       .setNeighbors(Vector("north" -> middle))
  destination .setNeighbors(Vector("west" -> middle))

  
  val player = Player(outside) // aloitussijainti

  var turnCount = 0 // kuinka monta vuoroa kulunut

  val timeLimit = 40  // vuorojen max. ennen autom. häviötä


  def isComplete = this.player.location == this.destination // && player.inventory.contains("remote") && player.inventory.contains("battery")

  def isOver = this.isComplete  || this.player.hasQuit || this.turnCount == this.timeLimit
  
  def welcomeMessage = "welcome to overtale"

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
