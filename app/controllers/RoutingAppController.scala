package controllers

import javax.inject._
import play.api.mvc._
import org.joda.time.DateTime

@Singleton
class RoutingAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action {
    Ok(views.html.routing_app.index())
  }

  def isValidSession(sessionId: String): Boolean = false

  def home = Action { implicit request =>
    request.headers.get("sessionId") match {
      case Some(sId) if isValidSession(sId) => Ok(views.html.routing_app.home())
      case _ => Redirect(routes.RoutingAppController.login())
    }
  }

  def login = Action {
    Ok(views.html.routing_app.login())
  }

  def fetchGreeting = Action {
    val hourOfDay = new DateTime().getHourOfDay
    val message = hourOfDay match {
      case x if x>=9 && x<12 => "Good Morning!!!"
      case y if y>=12 && y<17 => "Good Afternoon!!"
      case z if z>=17 && z<=20 => "Good Evening !!"
      case _ => "Sorry, application is available between 9:00 - 20:00 hrs only. We request you to try again during working hours."
    }
    Ok(message)
  }

  def jsDemo = Action {
    Ok(views.html.routing_app.js_demo())
  }
}
