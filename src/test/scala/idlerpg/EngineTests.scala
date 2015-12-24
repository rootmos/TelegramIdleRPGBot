import idlerpg.{Observable, Observer}
import org.scalatest._

class ObservableSpec extends FlatSpec with Matchers {

  val apperance = "Foobar!"
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

  def observable(apperance: String) = new Observable { def observe = apperance }

  "An Observer" should "return an iterator of Observables" in {
    val observer = new Observer { def observables = List[Observable]() }
    observer.observables shouldBe an [Iterable[_]]
  }

  it should "return the correct list of Observables" in {
    val a = observable("a")
    val b = observable("b")
    val c = observable("c")
    val observer = new Observer { def observables = List[Observable](a, b) }

    observer.observables should contain (a)
    observer.observables should contain (b)
    observer.observables should not contain (c)
  }

}
