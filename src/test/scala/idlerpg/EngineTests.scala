import idlerpg.{Observable, Observer, Entity}
import org.scalatest._

object Salt {
  private val rnd = new scala.util.Random
  private val length = 5
  def get = rnd.nextString(length)
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

  def observable = new Observable { val apperance = Salt.get; def observe = apperance }

  "An Observer" should "return an iterator of Observables" in {
    val observer = new Observer { def observables = List[Observable]() }
    observer.observables shouldBe an [Iterable[_]]
  }

  it should "return the correct list of Observables" in {
    val a = observable
    val b = observable
    val c = observable
    val observer = new Observer { def observables = List[Observable](a, b) }

    observer.observables should contain (a)
    observer.observables should contain (b)
    observer.observables should not contain (c)
  }

}

class EntitySpec extends FlatSpec with Matchers {

  def entity = new Entity { val identity = Salt.get; def id = identity }

  "An Entity" should "have an identity" in {
    val e = entity
    e.id should be (e.identity)
  }

  it should "have a String as its identity" in {
    entity.id shouldBe a [String]
  }

}
