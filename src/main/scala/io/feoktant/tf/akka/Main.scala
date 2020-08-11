package io.feoktant.tf.akka

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

object Main extends App {

  implicit val system: ActorSystem = ActorSystem("tf-example")
  implicit val ec: ExecutionContext = system.dispatcher

  private val route = new Route[Future](DatabaseImpl()).route
  private val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return

  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
