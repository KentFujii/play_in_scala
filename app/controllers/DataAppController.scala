package controllers

import javax.inject._
import play.api.mvc._
import play.api.db._

@Singleton
class DataAppController @Inject()(db: Database, cc: ControllerComponents) extends AbstractController(cc) {
  // https://github.com/KentFujii/test_db
  // https://dev.mysql.com/doc/employee/en/employees-installation.html
  // https://www.playframework.com/documentation/2.7.x/ScalaDatabase
  def fetchDBUser = Action {
    var result = "DB User:"
    db.withConnection { conn =>
      val rs = conn.createStatement().executeQuery("SELECT USER()")
      while (rs.next()) {
        result += rs.getString(1)
      }
    }
    Ok(result)
  }
}
