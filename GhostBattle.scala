package tekstipeli


import scala.util.Random

class GhostBattle(val actor: Player, val ghost: Entity) extends Battle(actor, ghost):


  def attack: String =
    if actor.has("sword") then
      val damageInflicted = Random.nextInt(34)
      this.ghost.takeDamage(damageInflicted)
      if this.ghost.health <= 0 then
        s"\nYour attack deals ${damageInflicted} damage.\numm... you do know you cant kill ghosts, right?"
      else
        s"\nYour attack deals ${damageInflicted} damage.\n${this.ghost.name} has ${this.ghost.health} HP left."
    else
      "\nzzzzzzz... (you can't expect to beat me without a weapon) zzzzzzzzzzz..."

  def give(treat: String): String =
    if actor.has(treat) then
      if treat == "treat" then
        this.actor.discard(treat)
        this.ghost.pacify()
        this.actor.endBattle()
        "\nMMMM! this shit is tasty asf! where did you find this?"
      else
        "\nzzzzzzzz... (that doesn't look appetizing) zzzzzzzz...\n (Kloopstanaab has a sweet tooth)"
    else
      "\nzzzzzzzzzz... (are they gone yet?) zzzzzzzzzzzzzzz...\n (You don't have that item.)"


  def chat: String =
    if math.random < 0.05 then
      this.ghost.pacify()
      this.actor.endBattle()
      "\noh, i'm rambling again\ni'll get out of your way"
    else
      "\nzzzzzz... (the ghost is pretending to sleep)"

  def pet: String = "zzzzzzzz... (appreciate the attempt but we ghosts are incorporeal, y'know) zzzzzzzzzzzzzzzzz..."

end GhostBattle