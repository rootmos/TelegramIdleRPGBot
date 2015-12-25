package idlerpg

import scala.collection.Iterable

object Entity {
  type ID = String
}

abstract class Entity {
  import Entity._
  def id: ID

  override def equals(other: Any) = other.isInstanceOf[Entity] && id == other.asInstanceOf[Entity].id
  override def hashCode = id.hashCode
}

trait EntityStore {
  def get(id: Entity.ID): Option[Entity]
}

trait Observer {
  def observables: Iterable[Observable]
}

trait Observable {
  type Observation = String
  def observe: Observation
  override def toString = observe
}

abstract class Person extends Entity with Observer with Observable {

}
