package io.feoktant.tf.akka

import akka.http.scaladsl.server
import akka.http.scaladsl.server.Directives._
import io.feoktant.tf.akka.Marshallable._

class Route[F[_] : Marshallable](db: Database[F]) {

  def route: server.Route = get {
    path("users" / IntNumber) { id =>
      complete(db.load(id))
    }
  }

}
