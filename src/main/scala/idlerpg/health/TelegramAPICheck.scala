package idlerpg.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck._

import javax.ws.rs.client.Client
import javax.ws.rs.core.MediaType

case class TelegramResult(ok: Boolean, result: User)
case class User(id: Int, first_name: String, username: String)

class TelegramAPICheck(client: Client, token: String) extends HealthCheck {
  val telegramAPI = client.target("https://api.telegram.org/bot" + token)
  val getMe = telegramAPI path "getMe" request MediaType.APPLICATION_JSON

  override def check() = getMe.get(classOf[TelegramResult]) match {
    case TelegramResult(true, _) => Result.healthy()
    case _ => Result.unhealthy("Telegram API did not return ok!")
  }

}
