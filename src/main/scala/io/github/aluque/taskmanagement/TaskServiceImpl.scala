package io.github.aluque.taskmanagement

import io.github.aluque.grpc.GrpcError
import io.github.aluque.taskmanagement.grpc.*
import org.apache.pekko.actor.typed.ActorSystem
import scala.concurrent.{ExecutionContext, Future}

class TaskServiceImpl(system: ActorSystem[?]) extends TaskService {
  private implicit val ec: ExecutionContext = system.executionContext

  override def getTask(in: GetTaskRequest): Future[Task] = {
    if (in.taskId.trim.isEmpty) {
      Future.failed(GrpcError.invalidArgument("Field 'task_id' cannot be empty."))
    } else {
      Future.successful(
        Task(
          id = in.taskId,
          title = s"Sample Task ${in.taskId}",
          description = "Created via TaskManagement gRPC service",
          status = "IN_PROGRESS"
        )
      )
    }
  }

  override def createTask(in: CreateTaskRequest): Future[Task] = {
    if (in.title.trim.isEmpty) {
      Future.failed(GrpcError.invalidArgument("Field 'title' cannot be empty."))
    } else {
      Future.successful(
        Task(
          id = java.util.UUID.randomUUID().toString.take(8),
          title = in.title,
          description = in.description,
          status = "CREATED"
        )
      )
    }
  }
}
