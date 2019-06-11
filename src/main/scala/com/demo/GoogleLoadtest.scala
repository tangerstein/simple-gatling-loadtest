package com.demo

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.checkerframework.checker.units.qual.min

import scala.concurrent.duration.TimeUnit

class GoogleLoadtest extends Simulation {
  val address = "http://google.de"
  val httpProtocol = http
    .baseUrl(address)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("GoogleLoadTest").repeat(10)(
    exec(http("GET_Google")
    .get("/")).pause(min= "200", max = "400", unit = TimeUnit.MILLISECONDS)
  )

  setUp(
    scn.inject(atOnceUsers(10))
  ).protocols(httpProtocol)
}