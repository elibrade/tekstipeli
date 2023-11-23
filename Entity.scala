package tekstipeli


class Entity(name: String, description: String) extends Area(name, description):

  private val initialHealth: Int = 100
  private var currentHealth: Int = initialHealth
  private var pacified: Boolean = false

  override def fullDescription: String = this.description + s" [${this.currentHealth} / ${this.initialHealth} HP]"

  def health: Int = currentHealth

  def takeDamage(damage: Int): Unit = currentHealth -= damage

  def pacify(): Unit = pacified = true

end Entity
