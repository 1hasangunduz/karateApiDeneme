package Scala
import io.gatling.core.Predef._
import scala.concurrent.duration._
import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}


class APISimulation extends Simulation{
  val protocol = {
    karateProtocol()
  }

  protocol.nameResolver = (req, ctx) => req.getUrlAndPath()

  val create = scenario("custom").exec(karateFeature("classpath:KarateApiTesting/KarateApi.feature"))
  setUp(
    create.inject(rampUsers(4) during  (2 seconds)).protocols(protocol)
  )

}
