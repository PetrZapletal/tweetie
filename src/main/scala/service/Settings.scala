package service

import akka.actor.{ExtensionKey, Extension, ExtendedActorSystem}
import scala.concurrent.duration._

/**
 * Created by petr on 5.4.14.
 */
object Settings extends ExtensionKey[Settings]

class Settings(system: ExtendedActorSystem) extends Extension {

  val host: String = "localhost"

  val port: Int = 8085

  val timeout: FiniteDuration = Duration(10, SECONDS)

}
