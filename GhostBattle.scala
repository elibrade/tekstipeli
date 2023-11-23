package tekstipeli


import scala.util.Random

class GhostBattle(actor: Player, ghost: Entity) extends Battle:


  def escape: String = ""
  
  def help: String = ""

  def attack: String =
    if actor.has(sword) then
      val damageInflicted = Random.nextInt(34)
      this.ghost.takeDamage(damageInflicted)
      if this.ghost.health <= 0 then
        this.ghost.pacify()
        this.actor.endBattle()
        "umm... you do know you cant kill ghosts, right?"
      else s"Your attack deals ${damageInflicted} damage"
    else "You can't expect to beat me without a weapon."

  def giveTreat(): String =
    if actor.has(treat) then
      "MMMM! This shit is tasty asf! Where did you find this?"
    else "zzzzzz... (the ghost is pretending to sleep)"

  def tellWhere(): String =
    this.ghost.pacify()
    this.actor.endBattle()
    "I must go find some more!"



  def chat: String =
    if math.random < 0.25 then
      this.ghost.pacify()
      this.actor.endBattle()
      "oh, i'm rambling again\ni'll get out of your way"
    else "zzzzzz... (the ghost is pretending to sleep)"

  def statusCheck: String = ""

  def quit: String = ""

  
end GhostBattle