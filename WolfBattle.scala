package tekstipeli

import scala.util.Random

class WolfBattle(val actor: Player, val wolf: Entity) extends Battle(actor, wolf):

  private var petCount: Int = 0
  private val petExcitement: Vector[String] = Vector(
    "You barely lifted your hand and Lesser Wolf got excited.",
    "You lightly touched the Wolf. It's already overexcited...",
    "You pet the Wolf. It raises its head up to meet your hand.",
    "You pet the Wolf. Its excitement knows no bounds.",
    "You don't even pet it. It gets more excited.",
    "There is no way to stop this madness.",
    "Lesser Wolf enters the realm of the clouds."
    )

  def pet: String =
    petCount += 1
    if petCount > 6 then
      wolf.pacify()
      actor.endBattle()
      petExcitement.last
    else petExcitement(petCount)


  def attack: String =
    if actor.has("sword") then
      val damageInflicted = Random.nextInt(34)
      this.wolf.takeDamage(damageInflicted)
      if this.wolf.health <= 0 then
        this.wolf.pacify()
        this.actor.endBattle()
        s"\nYour attack dealt ${damageInflicted} damage.\n(Tiny bark)\nLesser wolf is dead."
      else
        s"\nYour attack deals ${damageInflicted} damage.\n${this.wolf.name} has ${this.wolf.health} HP left."
    else
      "\n(Wag wag)\n(You don't have a weapon.)"

  def give(item: String): String =
    if actor.has(item) then
      "\n(Pant pant)\n(Lesser Wolf is on duty and doesn't accept gifts.)"
    else
      "\n(Pant pant)\n(You don't have that item.)"

  def chat: String = "\nLesser Wolf is really not paying attention. "

end WolfBattle