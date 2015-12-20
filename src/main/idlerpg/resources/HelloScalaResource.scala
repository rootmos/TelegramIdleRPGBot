package idlerpg.resources

import javax.ws.rs.{GET, Path, Produces}

case class Greeting(name: String, msg: String)

@Path("/hello-scala")
@Produces(Array("application/json"))
class HelloScalaResource {

  @GET
  def scalaSaysHi() = Greeting("Default", "Hello Scala world!")

}

