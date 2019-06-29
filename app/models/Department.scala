package models

import scalikejdbc._

case class Department(deptNo: String, deptName: String)

object Department {
  def findByDeptNo(deptNo: String)(implicit s: DBSession = AutoSession): Option[Department] = {
    sql"select dept_no, dept_name from products where dept_no = ${deptNo}"
      .map { rs => Department(rs) }.single.apply()
  }
}
