package idlerpg

import scala.collection.Iterable

abstract class Entity {
  val id: String
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
