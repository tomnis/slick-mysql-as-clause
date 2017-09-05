package org.mccandless

import org.junit.Test
import org.scalatest.Matchers
import org.scalatest.junit.JUnitSuite
import org.mccandless.Tables.profile.api._
import org.pmw.tinylog.Logger

class CatsTest extends JUnitSuite with Matchers {




  @Test
  def cats(): Unit = {
    Db.runSynchronously(Tables.Cats += Tables.CatsRow(0, "jean-luc", "siamese"))

    Db.runSynchronously(Tables.Cats.length.result) should be > 0



    val successfulRawQuery: String = "SELECT CONCAT(name, '') AS nombre FROM cats"
    val successfulResult: Seq[Map[String, Any]] = Db.runSynchronously(successfulRawQuery)
    Logger.info(s"successful result: $successfulResult")
    successfulResult.head should contain key "nombre"



    val failingRawQuery: String = "SELECT name AS nombre FROM cats"
    val failingResult: Seq[Map[String, Any]] = Db.runSynchronously(failingRawQuery)
    Logger.info(s"failed result: $failingResult")
    failingResult.head should contain key "nombre"
  }

}
