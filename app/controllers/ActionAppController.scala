package controllers

import javax.inject._
import play.api.mvc._
import models.Artist

@Singleton
class ActionAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def listArtist = Action {
    Ok(views.html.action_app.index(Artist.fetch))
  }

  def fetchArtistByName(name: String) = Action {
    Ok(views.html.action_app.index(Artist.fetchByName(name)))
  }

  def search(name: String, country: String) = Action {
    val result = Artist.fetchByNameOrCountry(name, country)
    if (result.isEmpty) {
      NoContent
    }
    else {
      Ok(views.html.action_app.index(result))
    }
  }

  def search2(name: Option[String], country: String) = Action {
    val result = name match {
      case Some(n) => Artist.fetchByNameOrCountry(n, country)
      case None => Artist.fetchByCountry(country)
    }
    if (result.isEmpty) {
      NoContent
    }
    else {
      Ok(views.html.action_app.index(result))
    }
  }

  def subscribe = Action { request =>
    Ok("received " + request.body + "\n")
  }
}
