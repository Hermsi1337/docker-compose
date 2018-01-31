package sample

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SampleService extends Simulation {

	val httpProtocol = http
		.baseURL("https://www.example.com")
		.acceptHeader("*/*")

	val scn = scenario("Sample Service Root")
		.exec(http("request_1")
			.get("/"))

	setUp(
    scn.inject(
			nothingFor(60 seconds)
      // ,atOnceUsers(10)
			// ,nothingFor(4 seconds)
      ,rampUsers(10) over(10 seconds)
			,nothingFor(5 seconds)
      // ,rampUsers(100) over(10 seconds)
			// ,nothingFor(5 seconds)
     //,rampUsersPerSec(30) to 300 during(10 minutes)
     ,heavisideUsers(100) over(30 minutes)
    )
  ).protocols(httpProtocol)
}
