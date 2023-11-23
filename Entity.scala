package tekstipeli


class Entity(val name: String, val description: String, val initialHealth: Int):

  private var currentHealth: Int = initialHealth
  private var pacified: Boolean = false

  def fullDescription: String = this.name + ", "+ this.description + s" [${this.currentHealth} / ${this.initialHealth} HP]"

  def health: Int = currentHealth

  def takeDamage(damage: Int): Unit = currentHealth -= damage

  def pacify(): Unit = pacified = true

  def isPacified: Boolean = pacified

end Entity
