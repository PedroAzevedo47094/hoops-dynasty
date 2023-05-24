package com.dam.hoopsdynasty.data

import android.content.Context
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase.Companion.getDatabase
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

object DataPopulator {
    private suspend fun parseTeamCsvFile(context: Context): List<Team> = withContext(Dispatchers.IO) {
        val inputStream = context.resources.openRawResource(R.raw.teams)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val teams = mutableListOf<Team>()

        try {
            var line: String? = reader.readLine()
            while (line != null) {
                val tokens = line.split(",")

                val name = tokens[0]
                val abbreviation = tokens[1]
                val conference = tokens[2]
                val division = tokens[3]
                val logo = tokens[4]
                val arena = tokens[5]

                // Create a new Team instance with the parsed data and add it to the list
                val team = Team(
                    name = name,
                    abbreviation = abbreviation,
                    conference = conference,
                    division = division,
                    logo = logo,
                    arena = arena,
                    players = emptyList(),
                    bench = emptyList(),
                    positions = mapOf(
                        "PG" to null,
                        "SG" to null,
                        "SF" to null,
                        "PF" to null,
                        "C" to null
                    ),
                    schedule = emptyList(),
                    gamesPlayed = emptyList(),
                    wins = 0,
                    losses = 0



                )
                teams.add(team)

                line = reader.readLine()
            }
        } finally {
            reader.close()
        }

        return@withContext teams
    }

    private suspend fun parsePlayerCsvFile(context: Context): List<Player> = withContext(Dispatchers.IO) {
        val inputStream = context.resources.openRawResource(R.raw.players)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val players = mutableListOf<Player>()

        try {
            var line: String? = reader.readLine()
            while (line != null) {
                val tokens = line.split(",")

                val id = tokens[0].toInt()
                val image = tokens[1]
                val firstName = tokens[2]
                val lastName = tokens[3]
                val position1 = tokens[4]
                val position2 = tokens[5]
                val rating = tokens[6].toFloat()
                val ppg = tokens[7].toFloat()
                val rpg = tokens[8].toFloat()
                val apg = tokens[9].toFloat()
                val bpg = tokens[10].toFloat()
                val spg = tokens[11].toFloat()
                val topg = tokens[12].toFloat()
                val orepg = tokens[13].toFloat()
                val drebpg = tokens[14].toFloat()
                val fga = tokens[15].toFloat()
                val fgm = tokens[16].toFloat()
                val fta = tokens[17].toFloat()
                val ftm = tokens[18].toFloat()
                val tpgm = tokens[19].toFloat()
                //val team: Team?

                // Create a new Team instance with the parsed data and add it to the list
                val player = Player(
                    id = id,
                    image = image,
                    firstName = firstName,
                    lastName = lastName,
                    position1 = position1,
                    position2 = position2,
                    rating = rating,
                    ppg = ppg,
                    rpg = rpg,
                    apg = apg,
                    bpg = bpg,
                    spg = spg,
                    topg = topg,
                    orepg = orepg,
                    drebpg = drebpg,
                    fga = fga,
                    fgm = fgm,
                    fta = fta,
                    ftm = ftm,
                    tpgm = tpgm,
                    team = null // foreign key = team

                )
                players.add(player)

                line = reader.readLine()
            }
        } finally {
            reader.close()
        }

        return@withContext players
    }



    fun  populateDatabase(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val teams = parseTeamCsvFile(context)
            val players = parsePlayerCsvFile(context)
            val database = getDatabase(context)
            database.teamDao().insertAllTeams(teams)
            database.playerDao().insertAllPlayers(players)
        }
    }
}
