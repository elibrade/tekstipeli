package tekstipeli 

class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  def fight(actor: Player) =
    // val battle = actor.currentBattle.get
    this.verb match    // Actions available within a fight
    case "fight"     => Some(actor.attack())
    case "chat"      => Some(actor.chat())
    case "go"        => Some("The ghost monster is blocking the way")
    case "quit"      => Some(actor.quit())
    case "inventory" => Some(actor.inventory)
    case other       => None


  def execute(actor: Player) = this.verb match    // Actions available when not fighting an Entity
    case "go"        => Some(actor.go(this.modifiers))
    case "rest"      => Some(actor.rest())
    case "xyzzy"     => Some("The grue tastes yummy.")
    case "quit"      => Some(actor.quit())
    case "get"       => Some(actor.get(this.modifiers))
    case "examine"   => Some(actor.examine(this.modifiers))
    case "drop"      => Some(actor.drop(this.modifiers))
    case "inventory" => Some(actor.inventory)
    case other       => None

  override def toString = s"$verb (modifiers: $modifiers)"

end Action

