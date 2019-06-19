package controllers

import javax.inject._
import models.Credentials
import play.api.mvc._
import play.api.data
import data.Form
import data.Forms._
import play.twirl.api.Html
import play.api.data.validation.{Invalid, Valid, ValidationError, Constraint}

@Singleton
class FormAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action {
    implicit request =>
      val content: play.twirl.api.Html = Html("Hello World")
      Ok(views.html.form_app.index("home") {
        content
      })
  }

  def register = Action {
    implicit request =>
      Ok(views.html.form_app.register(signupForm)).withNewSession
  }

  val signupForm = Form(
    mapping(
      "loginId" -> email,
      "password" -> nonEmptyText
    )(Credentials.apply)(Credentials.unapply) verifying("Username already in use",
      result => result match {
        case loginCredentials => !loginCredentials.loginId.equals("testUser@app.com")
      })
  )

  def newUser = Action(parse.multipartFormData) {
    implicit request =>
      signupForm.bindFromRequest().fold(
        formWithErrors => BadRequest(views.html.form_app.register(formWithErrors)),
        credentials => Ok("Welcome!!!!")
      )
  }

  def login = Action {
    Ok("login")
  }
}
