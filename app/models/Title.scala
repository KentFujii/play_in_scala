package models

import scalikejdbc._
import skinny.orm._
import org.joda.time._

case class Title(empNo: Long, title: String, fromDate: DateTime, toDate: DateTime)

object Title extends SkinnyNoIdMapper[Title] {
  override val tableName = "titles"
  override lazy val defaultAlias = createAlias("t")
  private[this] lazy val t = defaultAlias
  override def extract(rs: WrappedResultSet, rn: ResultName[Title]) = autoConstruct(rs, rn)

  def findByEmpNo(empNo: Long): Option[Title] = {
    where(sqls.eq(t.empNo, empNo)).apply().headOption
  }
}
