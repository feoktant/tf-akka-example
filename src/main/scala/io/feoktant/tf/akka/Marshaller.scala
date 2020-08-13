package io.feoktant.tf.akka

import akka.http.scaladsl.marshalling.ToResponseMarshaller

import scala.concurrent.Future
import scala.util.Try

trait Marshaller[F[_]] {
  def toResponseMarshaller[A : ToResponseMarshaller]: ToResponseMarshaller[F[A]]
}

object Marshaller {
  implicit def toRMTF[F[_], A : ToResponseMarshaller](implicit M: Marshaller[F]): ToResponseMarshaller[F[A]] =
    M.toResponseMarshaller

  implicit val futureMarshaller: Marshaller[Future] =
    new Marshaller[Future] {
      def toResponseMarshaller[A: ToResponseMarshaller]: ToResponseMarshaller[Future[A]] = implicitly
    }

  implicit val tryMarshaller: Marshaller[Try] =
    new Marshaller[Try] {
      def toResponseMarshaller[A: ToResponseMarshaller]: ToResponseMarshaller[Try[A]] = implicitly
    }
}
