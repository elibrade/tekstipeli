package tekstipeli

trait Battle(actor: Player, entity: Entity):

  // Abstract methods:
  def attack: String
  def chat: String
  def give(item: String): String
  def pet: String

  // Concrete methods:
  def go: String =
    s"${this.entity.name} is blocking the way!"

  def help: String =
    s"Commands available within a battle:\nattack\ngive\nchat\npet\ncheck\ninventory\nexamine\nescape\nquit"

  def statusCheck: String = s"\n${entity.fullDescription}"

  def escape: String =
    actor.endBattle()
    actor.go("west")
    "You evaded the monster."


end Battle