package controllers

import javax.inject._
import models.Credentials
import play.api.mvc._
import play.api._
import data.Form
import data.Forms._
import play.twirl.api.Html
import play.api.data.validation.{Invalid, Valid, ValidationError, Constraint}

@Singleton
class FormAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action {
    implicit request =>
      val content: play.twirl.api.Html = Html("Hello WOrld")
      Ok(views.html.form_app.index("home") {
        content
      })
  }

  def newUser = Action(parse.multipartFormData) {
    implicit request =>
      signupForm.bindFromRequest().fold(
        formWithErrors => BadRequest(views.html.form_app.register(formWithErrors)),
        credentials => Ok("Welcome!!!!")
      )
  }

  val signupForm = Form(
    mapping(
      "loginId" -> email,
      "password" -> nonEmptyText
    )(Credentials.apply)(Credentials.unapply) verifying("Username already in use",
      result => result match {
        case loginCredentials =>
          !loginCredentials.loginId.equals("testUser@app.com")
      })
  )

  def register = Action {
    implicit request =>
      Ok(views.html.form_app.register(signupForm)).withNewSession
  }

  def login = Action {
    Ok("login")
  }

  val validUserName = """[A-Za-z0-9]*""".r
  val userNameCheck: Constraint[String] = Constraint("userName")({
    text =>
      val error = text match {
        case validUserName() => Nil
        case _ => Seq(ValidationError("only digits and alphabet ar allowed in userName"))
      }
      if (error.isEmpty) Valid else Invalid(error)
  })
}
