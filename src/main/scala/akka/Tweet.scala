package akka

import _root_.spray.json.DefaultJsonProtocol

/**
 * Created by petr on 6.4.14.
 */

object Tweet extends DefaultJsonProtocol{
  implicit val format = jsonFormat2(apply)
}

case class Tweet (user: String, content: String)
