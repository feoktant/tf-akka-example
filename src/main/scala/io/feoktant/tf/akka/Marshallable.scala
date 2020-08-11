package io.feoktant.tf.akka

import akka.http.scaladsl.marshalling.ToResponseMarshaller

import scala.concurrent.Future
import scala.util.Try

trait Marshallable[F[_]] {
  def marshaller[A : ToResponseMarshaller]: ToResponseMarshaller[F[A]]
}

object Marshallable {
  implicit def marshaller[F[_], A : ToResponseMarshaller](implicit M: Marshallable[F]): ToResponseMarshaller[F[A]] =
    M.marshaller

  implicit val futureMarshaller: Marshallable[Future] =
    new Marshallable[Future] {
      def marshaller[A: ToResponseMarshaller]: ToResponseMarshaller[Future[A]] = implicitly
    }

  implicit val tryMarshaller: Marshallable[Try] =
    new Marshallable[Try] {
      def marshaller[A: ToResponseMarshaller]: ToResponseMarshaller[Try[A]] = implicitly
    }
}
