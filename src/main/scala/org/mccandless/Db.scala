package org.mccandless

import slick.dbio.DBIO
import slick.jdbc.MySQLProfile.backend.Database
import org.mccandless.Implicits.getResultAsMap
import org.mccandless.Tables.profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Db {

  private val connection: Database = Database.forConfig("slick", Environment.config)

  /**
    * Runs `action` and blocks until the result is available.
    *
    * @param action
    * @tparam Row
    * @return
    */
  def runSynchronously[Row](action: DBIO[Row]): Row = {
     Await.result(this.connection.run(action), Duration.Inf)
  }


  /**
    * Converts `rawQuery` to a [[DBIO]], runs it, and blocks until the result is available.
    *
    * @param rawQuery
    * @return
    */
  def runSynchronously(rawQuery: String): Seq[Map[String, Any]] = {
    val query = sql"#$rawQuery".as[Map[String, Any]]
    this.runSynchronously(query)
  }
}
