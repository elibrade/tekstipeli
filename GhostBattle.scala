package tekstipeli


import scala.util.Random

class GhostBattle(actor: Player, ghost: Entity) extends Battle:


  def escape: String = ""
  
  def help: String = ""

  def attack: String =
    val damageInflicted = Random.nextInt(34)
    this.ghost.takeDamage(damageInflicted)
    if this.ghost.health <= 0 then
      this.ghost.pacify()
      this.actor.endBattle()
      "umm... you do know you cant kill ghosts, right?"
    else s"Your attack deals ${damageInflicted} damage"

  def chat: String =
    if math.random < 0.25 then
      this.ghost.pacify()
      this.actor.endBattle()
      "oh, i'm rambling again\ni'll get out of your way"
    else "zzzzzz... (the ghost is pretending to sleep)"

  def statusCheck: String = ""

  def quit: String = ""

  
end GhostBattle