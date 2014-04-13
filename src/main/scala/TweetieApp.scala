import akka.actor.{Props, ActorSystem}
import service.{Settings, Service}

/**
 * Created by petr on 5.4.14.
 */
object TweetieApp extends App {

  val system = ActorSystem("tweetie")
  val host = Settings(system).host
  val port = Settings(system).port
  val timeout = Settings(system).timeout

  println(s"Tweetie server listening on ${host}:${port}")

  system.actorOf(Props(new Service(host, port, timeout)), "tweetie-service")

  readLine("Press any key to ABORT ... \n")
  system.shutdown()

  //system.awaitTermination
}
