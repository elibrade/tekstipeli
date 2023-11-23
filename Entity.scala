package tekstipeli


class Entity(name: String, description: String) extends Area(name, description):

  private val healthPoints: Int = 100
  private var takenDamage: Int = 0

  override def fullDescription: String = this.description + s"[${this.healthPoints - this.takenDamage} / ${this.healthPoints} HP]"

  def takeDamage(damage: Int) = takenDamage += damage 

end Entity
