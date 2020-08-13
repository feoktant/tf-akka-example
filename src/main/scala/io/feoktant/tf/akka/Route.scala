package io.feoktant.tf.akka

import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder
import io.feoktant.tf.akka.Marshaller._

class Route[F[_] : Marshaller](db: Database[F])
  extends FailFastCirceSupport {

  private implicit val userEncoder: Encoder[User] = deriveEncoder

  def route: server.Route = get {
    path("users" / IntNumber) { id =>
      complete(db.load(id))
    }
  }

}
