package io.github.aluque.taskmanagement

import io.github.aluque.grpc.PekkoGrpcApp
import io.github.aluque.taskmanagement.grpc.TaskServiceHandler
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.http.scaladsl.model.{HttpRequest, HttpResponse}
import scala.concurrent.Future

object Server extends PekkoGrpcApp("TaskManagementService") {
  override def buildHandler(system: ActorSystem[?]): HttpRequest => Future[HttpResponse] = {
    val service = new TaskServiceImpl(system)
    TaskServiceHandler.withServerReflection(service)(system)
  }
}
