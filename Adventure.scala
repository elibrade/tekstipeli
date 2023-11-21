package tekstipeli

class Adventure:

  val title = "Teemu teekkari vs. kelju kylteri"

  // kartan määrittely:
  private val middle      = Area("Middle", "Middle of the Dungeon")
  private val south       = Area("South", "Southern Dungeon")
  private val home        = Area("Home", "You've arrived home")
  private val destination = home

  middle      .setNeighbors(Vector("south" -> south, "east" -> destination))
  south       .setNeighbors(Vector("north" -> middle))
  destination .setNeighbors(Vector("west" -> middle))


  val player = Player(middle) // aloitussijainti

  var turnCount = 0 // kuinka monta vuoroa kulunut

  val timeLimit = 40  // vuorojen max. ennen autom. häviötä


  def isComplete = this.player.location == this.destination // && player.inventory.contains("remote") && player.inventory.contains("battery")

  def isOver = this.isComplete  || this.player.hasQuit || this.turnCount == this.timeLimit

  
  def welcomeMessage = ""

  def goodbyeMessage =
    if this.isComplete then
      ""
    else if this.turnCount == this.timeLimit then
      ""
    else  // game over due to player quitting
      ""

  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure
