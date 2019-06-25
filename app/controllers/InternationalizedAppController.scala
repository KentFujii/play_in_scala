package controllers

import javax.inject._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n._

@Singleton
class InternationalizedAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with I18nSupport {
  val enquiryForm = Form(
    tuple(
      "emailId" -> email,
      "userName" -> optional(text),
      "question" -> nonEmptyText
    )
  )

  def index = Action { implicit request =>
    Redirect(routes.InternationalizedAppController.askUs)
  }

  def askUs = Action { implicit request =>
    val messages: Messages = request.messages
    val title: String = messages("enquery.title")
    val askUs: String = messages("enquery.askUs")
    Ok(views.html.internationalized_app.index(enquiryForm, title, askUs))
  }

  def enquire = Action { implicit request =>
    val messages: Messages = request.messages
    val title: String = messages("enquery.title")
    val askUs: String = messages("enquery.askUs")
    enquiryForm.bindFromRequest.fold(
      errors => BadRequest(views.html.internationalized_app.index(errors, title, askUs)),
      query => {
        println(query.toString)
        Redirect(routes.InternationalizedAppController.askUs)
      }
    )
  }
}
