package controllers

import javax.inject._
import play.api.mvc._
import play.api.db.DB
// import play.api.Play.current

@Singleton
class DataAppController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  // https://github.com/KentFujii/test_db
  // https://dev.mysql.com/doc/employee/en/employees-installation.html
  def fetchDBUser = Action {
    var result = "DB User:"
    DB.withConnection { conn =>
      val rs = conn.createStatement().executeQuery("SELECT USER()")
      while (rs.next()) {
        result += rs.getString(1)
      }
    }
    Ok(result)
  }
}
