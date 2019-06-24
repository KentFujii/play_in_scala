package controllers

import javax.inject._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n._

@Singleton
class InternationalizedAppController @Inject()(cc: ControllerComponents, implicit override val messagesApi: MessagesApi) extends AbstractController(cc) with I18nSupport {
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
    println(request.lang)
    val messages = request.messages
    println(messages.lang)
    println(messages("enquiry.title"))
    Ok(views.html.internationalized_app.index(enquiryForm))
  }

  def enquire = Action { implicit request =>
    enquiryForm.bindFromRequest.fold(
      errors => BadRequest(views.html.internationalized_app.index(errors)),
      query => {
        println(query.toString)
        Redirect(routes.InternationalizedAppController.askUs)
      }
    )
  }
}
