package tekstipeli


import scala.util.Random

class GhostBattle(val actor: Player, val ghost: Entity) extends Battle:

  def escape: String =
    actor.endBattle()
    actor.go("west")
    "You evaded the monster."
  
  def help: String = s"Commands available within a battle:\nattack\ngive\nchat\nquit\nescape"

  def attack: String =
    if actor.has("sword") then
      val damageInflicted = Random.nextInt(34)
      this.ghost.takeDamage(damageInflicted)
      if this.ghost.health <= 0 then
        this.ghost.pacify()
        this.actor.endBattle()
        s"\nYour attack deals ${damageInflicted} damage.\numm... you do know you cant kill ghosts, right?"
      else s"\nYour attack deals ${damageInflicted} damage.\n${this.ghost.name} has ${this.ghost.health} HP left."
    else "\nzzzzzzz... (you can't expect to beat me without a weapon) zzzzzzzzzzz..."

  def giveTreat(treat: String): String =
    if actor.has(treat) then
      this.actor.discard(treat)
      this.ghost.pacify()
      this.actor.endBattle()
      "\nMMMM! this shit is tasty asf! where did you find this?"
    else "\nzzzzzzzzzz... (are they gone yet?) zzzzzzzzzzzzzzz...\n (You don't have that item.)"

/*  def tellWhere(): String = // TODO: Mitä tää tekee?
    this.ghost.pacify()
    this.actor.endBattle()
    "I must go find some more!"*/

  def chat: String =
    if math.random < 0.10 then
      this.ghost.pacify()
      this.actor.endBattle()
      "\noh, i'm rambling again\ni'll get out of your way"
    else "\nzzzzzz... (the ghost is pretending to sleep)"

  def statusCheck: String = ""

end GhostBattle