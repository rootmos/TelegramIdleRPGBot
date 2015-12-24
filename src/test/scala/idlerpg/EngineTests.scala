import idlerpg.{Observable, Observer, Entity, EntityStore}
import org.scalatest._
import scala.collection.mutable

object Salt {
  private val rnd = new scala.util.Random
  private val length = 5
  def get = rnd.nextString(length)

  def entity(identity: Entity.ID = get): Entity = new Entity { def id = identity }
  def observable: Observable = new Observable { val apperance = get; def observe = apperance }
}

class ObservableSpec extends FlatSpec with Matchers {

  val apperance = Salt.get
  val o: Observable = new Observable {
    def observe = apperance
  }

  "An Observable" can "be observed" in {
    o.observe should be (apperance)
  }

  it can "be printed" in {
    o.toString should be (apperance)
  }

  it should "return a String" in {
    o.observe shouldBe a [String]
  }
}

class ObserverSpec extends FlatSpec with Matchers {

  "An Observer" should "return an iterator of Observables" in {
    val observer = new Observer { def observables = List[Observable]() }
    observer.observables shouldBe an [Iterable[_]]
  }

  it should "return the correct list of Observables" in {
    val a = Salt.observable
    val b = Salt.observable
    val c = Salt.observable
    val observer = new Observer { def observables = List[Observable](a, b) }

    observer.observables should contain (a)
    observer.observables should contain (b)
    observer.observables should not contain (c)
  }

}

class EntitySpec extends FlatSpec with Matchers {

  "An Entity" should "have an identity" in {
    val identity = Salt.get
    val e = Salt.entity(identity)
    e.id should be (identity)
  }

  it should "have an identity with the correct type" in {
    val entity = Salt.entity()
    entity.id shouldBe a [Entity.ID]
  }

}

class EntityStoreSpec extends FlatSpec with Matchers {

  def entityStore(initialEntities: List[Entity]): EntityStore = new EntityStore {
    val entities = mutable.HashMap.empty[Entity.ID, Entity]

    for (e <- initialEntities) {
      entities += (e.id -> e)
    }

    def get(id: Entity.ID) = entities.get(id)
  }

  "The get method" should "return the same instance wrapped in Some" in {
    val e = Salt.entity()
    val store = entityStore(e :: Nil)

    store.get(e.id) match {
      case Some(x) => x should be theSameInstanceAs e
      case None => fail("Entity not found!")
    }
  }

  it should "return None if the Entity does not exist" in {
    val store = entityStore(Nil)
    store.get(Salt.get) should be (None)
  }

}
