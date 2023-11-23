package tekstipeli

import scala.collection.mutable.Map

class Entity(name: String, description: String) extends Area(name, description):

  override def fullDescription: String = this.description

end Entity
