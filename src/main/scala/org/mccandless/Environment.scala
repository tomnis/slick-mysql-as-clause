package org.mccandless

import com.typesafe.config.{Config, ConfigFactory}

object Environment {

  private val defaults: String =
    s"""
       |slick {
       |  driver = com.mysql.jdbc.Driver
       |  url = "jdbc:mysql://localhost:3307/slick?user=root&password=1234"
       |}
     """.stripMargin

  val config: Config = ConfigFactory.systemProperties()
    .withFallback(ConfigFactory.parseString(this.defaults))
}
