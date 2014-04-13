package akka

import akka.actor.{ActorLogging, Actor}

/**
 * Created by petr on 6.4.14.
 */
class ClientActor extends Actor with ActorLogging{

  var tweets : List[Tweet] = Nil

  var storedCompletionFunc : Option[Seq[Tweet] => Unit] = None

  override def receive: Receive = {
    case completionFunc : (Seq[Tweet] => Unit) =>
      if(tweets.isEmpty)
        storedCompletionFunc = Some(completionFunc)
      else
        completionFunc(tweets)

    case tweet: Tweet =>
      tweets +:= tweet
      for(func <- storedCompletionFunc){
        func(tweets)
        tweets = Nil
        storedCompletionFunc = None
      }
  }
}
