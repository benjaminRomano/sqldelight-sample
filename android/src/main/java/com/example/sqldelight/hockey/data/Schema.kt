package com.example.sqldelight.hockey.data

import com.example.sqldelight.hockey.HockeyDb
import com.example.sqldelight.hockey.data.PlayerVals.Position
import com.example.sqldelight.hockey.data.PlayerVals.Shoots
import com.example.sqldelight.hockey.data.second.Teams
import com.example.sqldelight.hockey.schema.Teams2
import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import java.util.GregorianCalendar

fun createQueryWrapper(driver: SqlDriver): HockeyDb {
  return HockeyDb(
      driver = driver,
      teamAdapter = Team.Adapter(
          foundedAdapter = DateAdapter()
      ),
          teamsAdapter = Teams.Adapter(
                  foundedAdapter = DateAdapter()
          ),
          teams2Adapter = Teams2.Adapter(
                  foundedAdapter = DateAdapter()
          ),
      playerAdapter = Player.Adapter(
          shootsAdapter = EnumColumnAdapter(),
          positionAdapter = EnumColumnAdapter(),
          birth_dateAdapter = DateAdapter()
      )
  )
}

object Schema : SqlDriver.Schema by HockeyDb.Schema {
  override fun create(driver: SqlDriver) {
    HockeyDb.Schema.create(driver)

    // Seed data time!
    createQueryWrapper(driver).apply {

      val ducks = "Anaheim Ducks"
      val pens = "Pittsburgh Penguins"
      val sharks = "San Jose Sharks"

      // Populate teams.
      teamQueries.insertTeam(ducks, GregorianCalendar(1993, 3, 1), "Randy Carlyle", true)
      teamQueries.insertTeam(pens, GregorianCalendar(1966, 2, 8), "Mike Sullivan", true)
      teamQueries.insertTeam(sharks, GregorianCalendar(1990, 5, 5), "Peter DeBoer", false)

      playerQueries.insertPlayer(
          "Corey", "Perry", 10, ducks, 30, 210F, GregorianCalendar(1985, 5, 16),
          Shoots.RIGHT, Position.RIGHT_WING
      )
      playerQueries.insertPlayer(
          "Ryan", "Getzlaf", 15, ducks, 30, 221F, GregorianCalendar(1985, 5, 10),
          Shoots.RIGHT, Position.CENTER
      )
      teamQueries.setCaptain(15, ducks)

      playerQueries.insertPlayer(
          "Sidney", "Crosby", 87, pens, 28, 200F, GregorianCalendar(1987, 8, 7),
          Shoots.LEFT, Position.CENTER
      )
      teamQueries.setCaptain(87, pens)

      playerQueries.insertPlayer(
          "Erik", "Karlsson", 65, sharks, 28, 190F, GregorianCalendar(1990, 5, 31),
          Shoots.RIGHT, Position.DEFENSE
      )

      playerQueries.insertPlayer(
          "Joe", "Pavelski", 8, sharks, 31, 194F, GregorianCalendar(1984, 7, 18),
          Shoots.RIGHT, Position.CENTER
      )
      teamQueries.setCaptain(8, sharks)
    }
  }
}