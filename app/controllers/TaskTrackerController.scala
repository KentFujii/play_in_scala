package controllers

import javax.inject._
import play.api.mvc._
import models.Task

@Singleton
class TaskTrackerController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action {
    Redirect(routes.TaskTrackerController.tasks)
  }

  def tasks = Action {
    Ok(views.html.task_tracker.index(Task.all))
  }

  def newTask = Action(parse.formUrlEncoded) { implicit request =>
    Task.add(request.body.get("taskName").get.head)
    Redirect(routes.TaskTrackerController.index)
  }

  def deleteTask(id: Int) = Action {
    Task.delete(id)
    Ok
  }
}
