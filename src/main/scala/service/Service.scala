package service

import akka.actor.{ActorRef, Props, ActorLogging}
import akka.io.IO
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.ExecutionContext
import spray.can.Http
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport
import spray.routing.authentication.BasicAuth
import spray.routing.{Route, HttpServiceActor}

import ExecutionContext.Implicits.global
import akka.{ClientActor, Tweet}

// Route -> function, RequestContext => Unit

class Service(host: String, port: Int, timeout: FiniteDuration) extends HttpServiceActor with ActorLogging with SprayJsonSupport {

  //val tweetsStorage = new mutable.HashMap[String, Set[akka.Tweet]] with mutable.MultiMap[String, akka.Tweet]


  //binding ...
  IO(Http)(context.system) ! Http.Bind(self, host, port)


  override def receive: Receive = runRoute(navigationRoute ~ commandRoute)

  //UI related requests
  def navigationRoute: Route = {
    path("") {
      getFromResource("web/index.html")
    } ~
      getFromResourceDirectory("web")
  }

  def commandRoute: Route =
  authenticate(BasicAuth(AlwaysAuthAuthenticator, "Client"))
  {
    user =>
      pathPrefix("api") {
          path("tweets") {
            get {
              produce(instanceOf[Seq[Tweet]]) { completionFunc => ctx =>
              log.debug(s"${user.username} is asking for tweets")
              getClientActorByName(user.username) ! completionFunc
              }
            } ~
              post {
                entity(as[Tweet]) {
                  tweet =>
                    complete {
                      if(tweet.user != user.username) log.warning(s"akka.Tweet.user [${tweet.user}] != user.username [${user.username}]")
                      log.debug(s"${tweet.user} published some tweet [${tweet.content}]")
                      //tweetsStorage.addBinding(tweet.user, new akka.Tweet(tweet.user, tweet.content, System.currentTimeMillis))

                      //StatusCodes.Accepted
                      //"TWEET ACCEPTED"
                      //TODO send who is interesting in
                      //TODO add filter !

                      /*
                      context.children foreach { child =>
                        log.debug(child.toString())
                        child ! tweet
                      }
                     */

                      getClientActorByName(tweet.user) ! tweet

                      StatusCodes.NoContent
                    }
                }
              }
          } ~
          path ("kill") {
            get{
              complete {
                log.info("KILL command received!!\n")
                //log.debug(tweetsStorage.toString())
                context.system.shutdown     //TODO wait some time
                "SHUTDOWN COMMAND"
              }
            }
          }
      }
  }


  def getClientActorByName(name: String): ActorRef = {
    context.child(name) getOrElse context.actorOf(Props[ClientActor], name)
  }

}
