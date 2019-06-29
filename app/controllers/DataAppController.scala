package controllers

import javax.inject._
import play.api.mvc._
import play.api.db._
import models.Department

@Singleton
class DataAppController @Inject()(db: Database, cc: ControllerComponents) extends AbstractController(cc) {
  def fetchEmployees = Action {
    var result = ""
    db.withConnection { conn =>
      val rs = conn.createStatement().executeQuery("SELECT first_name FROM employees LIMIT 5;")
      while (rs.next()) {
        result += rs.getString(1)
        result += "\n"
      }
    }
    Ok(result)
  }

  // http://scalikejdbc.org/
  def fetchDepartments = Action {
    val department = Department.findByDeptNo("d009")
    println(department)
    // Ok(Department)
    Ok("")
  }

  // http://skinny-framework.org/
  def fetchTitles = Action {
    Ok("")
  }
}
