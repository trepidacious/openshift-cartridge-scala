package org.rebeam

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._

import _root_.argonaut._, Argonaut._
import org.http4s.argonaut._

import scalaz._, scalaz.concurrent._, Scalaz._
import scalaz.effect.{ SafeApp, IO }
import scalaz.stream.Process

import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.middleware.CORS

import scala.util.Try

object Http4sDemoApp extends App {

  val service = HttpService {

    case GET -> Root =>
      Ok("Try visiting /echo/someIntegerValue")

    case GET -> Root / "echo" / IntVar(n) =>
      Ok("Echoing " + n + "!")

  }

  def envStringOrElse(s: String, d: String) = sys.env.getOrElse(s, d)
  def envIntOrElse(s: String, d: Int) = sys.env.get(s).flatMap(v => Try(v.toInt).toOption).getOrElse(d)

  val interface = envStringOrElse("OPENSHIFT_SCALA_IP", "localhost")
  val port = envIntOrElse("OPENSHIFT_SCALA_PORT", 8080)

  println("Interface " + interface + ", port " + port)

  BlazeBuilder.bindHttp(8080)
    .mountService(service, "/")
    .run
    .awaitShutdown()
}

