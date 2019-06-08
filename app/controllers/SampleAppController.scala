package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class SampleAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index() = Action { implicit request: Request[AnyContent] =>
    val content = "This is the content for the sample app"
    Ok(views.html.sample_app(content))
  }
}
