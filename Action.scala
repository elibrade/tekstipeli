package tekstipeli 

class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  // Kaikki fightin ja executen metodit palauttaa Stringin, muuten TextUI.playTurn.turnReport vittuilee!!!

  def fight(actor: Player): Option[String] = this.verb match  // Actions available within a fight
    case "attack"        => Some(actor.attack())
    case "chat"          => Some(actor.chat())
    case "give"          => Some(actor.giveTreat(this.modifiers))
    // case "outside"    => Some(actor.tellWhere())
    case "go"            => Some(actor.battleGo)
    case "quit"          => Some(actor.quit())
    case "inventory"     => Some(actor.inventory)
    case "help"          => Some(actor.battleHelp)
    case "escape"        => Some(actor.escape())
    case other           => None


  def execute(actor: Player): Option[String] = this.verb match    // Actions available when not fighting an Entity
    case "go"            => Some(actor.go(this.modifiers))
    case "rest"          => Some(actor.rest())
    case "xyzzy"         => Some("The grue tastes yummy.")
    case "quit"          => Some(actor.quit())
    case "get"           => Some(actor.get(this.modifiers))
    case "examine"       => Some(actor.examine(this.modifiers))
    case "drop"          => Some(actor.drop(this.modifiers))
    case "inventory"     => Some(actor.inventory)
    case other           => None

  override def toString = s"$verb (modifiers: $modifiers)"

end Action

