package io.feoktant.tf.akka

import java.util.UUID

import scala.concurrent.Future

trait Database[F[_]] {
  def load(id: Int): F[User]
  def save(user: User): F[Unit]
}

case class DatabaseImpl() extends Database[Future] {
  override def load(id: Int): Future[User] =
    Future.successful(User(UUID.randomUUID(), s"username$id"))

  override def save(user: User): Future[Unit] =
    Future.unit
}
