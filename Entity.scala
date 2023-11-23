package tekstipeli


class Entity(name: String, description: String, val initialHealth: Int):

  private var currentHealth: Int = initialHealth
  private var pacified: Boolean = false

  def fullDescription: String = this.description + s" [${this.currentHealth} / ${this.initialHealth} HP]"

  def health: Int = currentHealth

  def takeDamage(damage: Int): Unit = currentHealth -= damage

  def pacify(): Unit = pacified = true

end Entity
