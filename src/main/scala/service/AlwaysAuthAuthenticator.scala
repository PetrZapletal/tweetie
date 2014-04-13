package service

import scala.concurrent.{Promise, Future}
import spray.routing.authentication.{UserPass, BasicUserContext, UserPassAuthenticator}

/**
 * Created by petr on 6.4.14.
 */
object AlwaysAuthAuthenticator extends UserPassAuthenticator[BasicUserContext] {
  override def apply(userPass: Option[UserPass]): Future[Option[BasicUserContext]] = {
    val context =
    userPass flatMap {
      case UserPass(user, _) => Some(BasicUserContext(user))
      case _ => None
    }
    Promise.successful(context).future
    //Future.successful(context)
  }
}
