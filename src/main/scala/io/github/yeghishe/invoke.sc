import com.amazonaws.handlers.RequestHandler2
import com.amazonaws.regions.Regions
import com.amazonaws.services.lambda.invoke.{LambdaFunction, LambdaInvokerFactory}
import com.amazonaws.services.lambda.{AWSLambdaClient, AWSLambdaClientBuilder}
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import io.circe.{Encoder, Json}
import io.github.yeghishe.MySimpleHander

import scala.beans.BeanProperty

val mapper: ObjectMapper = com.amazonaws.util.json.Jackson.getObjectMapper
// doesn't get registered
mapper.registerModule(DefaultScalaModule)

case class Name(@BeanProperty name: String) {
//  def this() = this("")
}


class Result(@BeanProperty var message: String) {
  def this() = this("")
}

//val g = new Result

trait GreetingService {
  @LambdaFunction(functionName = "lambda-tim")
  def hello(input: Name): Result
}

val lambda = new AWSLambdaClient()
lambda.configureRegion(Regions.EU_CENTRAL_1)

val service = LambdaInvokerFactory.builder()
  .lambdaClient(AWSLambdaClientBuilder.standard().build())
  .build(classOf[GreetingService])

val input = Name("Tim")


com.amazonaws.util.json.Jackson.toJsonString(input)

mapper.writeValueAsString(input)

val objectMapper = new ObjectMapper()
objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true)
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
objectMapper.registerModule(DefaultScalaModule)
objectMapper.writeValueAsString(input)

com.amazonaws.util.json.Jackson.getObjectMapper.registerModule(DefaultScalaModule).writeValueAsString(input)

import io.circe.generic.auto._
Encoder[Name].apply(input).noSpaces

//val gr = new Result()
//gr.setMessage("string")
//gr



new ObjectMapper().readValue(""""message":"con""", classOf[Result])

//val greeting = service.hello(input)


Thread.sleep(3000)
//greeting
