package org.mccandless

import java.sql.ResultSetMetaData

import slick.jdbc.{GetResult, PositionedResult}

object Implicits {


  /**
    * Get sql query results as a [[Map]]
    */
  implicit val getResultAsMap: GetResult[Map[String, Any]] = GetResult({ pr: PositionedResult =>
    val metaData: ResultSetMetaData = pr.rs.getMetaData
    val columns: Iterable[(String, Any)] = (1 to pr.numColumns).map { index =>
      metaData.getColumnLabel(index) -> pr.nextObject()
    }

    columns.toMap
  })
}
