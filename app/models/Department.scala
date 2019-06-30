package models

import scalikejdbc._

case class Department(deptNo: String, deptName: String)

object Department extends SQLSyntaxSupport[Department] {
  override def tableName = "departments"
  val d = Department.syntax("d")

  def apply(d: ResultName[Department])(rs: WrappedResultSet): Department = new Department(
    deptNo = rs.get(d.deptNo),
    deptName = rs.get(d.deptName)
  )
  def apply(d: SyntaxProvider[Department])(rs: WrappedResultSet): Department = apply(d.resultName)(rs)

  def findByDeptNo(deptNo: String)(implicit s: DBSession = AutoSession): Option[Department] = withSQL {
    select.from(Department as d).where.eq(d.deptNo, deptNo)
  }.map(Department(d)).single.apply()
}
