package com.dam.hoopsdynasty.data

import android.content.Context
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.dao.GameDao
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase.Companion.getDatabase
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Season
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader


object DataPopulator {


    private var guards49_60 = mutableListOf<Player>()
    private var forwards49_60 = mutableListOf<Player>()
    private var centers49_60 = mutableListOf<Player>()

    private var guards61_66 = mutableListOf<Player>()
    private var forwards61_66 = mutableListOf<Player>()
    private var centers61_66 = mutableListOf<Player>()

    private var guards67_70 = mutableListOf<Player>()
    private var forwards67_70 = mutableListOf<Player>()
    private var centers67_70 = mutableListOf<Player>()

    private var guards71_90 = mutableListOf<Player>()
    private var forwards71_90 = mutableListOf<Player>()
    private var centers71_90 = mutableListOf<Player>()

    private var guards91_max = mutableListOf<Player>()
    private var forwards91_max = mutableListOf<Player>()
    private var centers91_max = mutableListOf<Player>()

    private var playersInTeam = mutableListOf<Player>()

    private var easternConference = mutableListOf<Team>()
    private var westernConference = mutableListOf<Team>()

    private var pacificDivision = mutableListOf<Team>()
    private var northwestDivision = mutableListOf<Team>()
    private var southwestDivision = mutableListOf<Team>()
    private var centralDivision = mutableListOf<Team>()
    private var southeastDivision = mutableListOf<Team>()
    private var atlanticDivision = mutableListOf<Team>()


    private suspend fun parsePlayerCsvFile(context: Context): List<Player> =
        withContext(Dispatchers.IO) {
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


    private fun dividePlayersByRating(players: List<Player>) {
        players.forEach { player ->
            when {
                player.rating < 61 -> when (player.position1) {
                    "Guard" -> guards49_60.add(player)
                    "Forward" -> forwards49_60.add(player)
                    "Center" -> centers49_60.add(player)
                }

                player.rating < 67 -> when (player.position1) {
                    "Guard" -> guards61_66.add(player)
                    "Forward" -> forwards61_66.add(player)
                    "Center" -> centers61_66.add(player)
                }

                player.rating < 71 -> when (player.position1) {
                    "Guard" -> guards67_70.add(player)
                    "Forward" -> forwards67_70.add(player)
                    "Center" -> centers67_70.add(player)
                }

                player.rating < 91 -> when (player.position1) {
                    "Guard" -> guards71_90.add(player)
                    "Forward" -> forwards71_90.add(player)
                    "Center" -> centers71_90.add(player)
                }

                else -> when (player.position1) {
                    "Guard" -> guards91_max.add(player)
                    "Forward" -> forwards91_max.add(player)
                    "Center" -> centers91_max.add(player)
                }
            }

        }

    }

    private fun assingPlayersToTeam(): List<Player> {
        val teamPlayers = mutableListOf<Player>()

        val g = "g"
        val f = "f"
        val c = "c"

        // 1. Assign 1st player from each position to the team (PG, SG, SF, PF, C)
        getPlayer(g)?.let { teamPlayers.add(it) }
        getPlayer(g)?.let { teamPlayers.add(it) }
        getPlayer(f)?.let { teamPlayers.add(it) }
        getPlayer(f)?.let { teamPlayers.add(it) }
        getPlayer(c)?.let { teamPlayers.add(it) }

        // 2. Assign 2nd players from each position to the team in reverse order (C, PF, SF, SG, PG)
        getPlayer(c)?.let { teamPlayers.add(it) }
        getPlayer(f)?.let { teamPlayers.add(it) }
        getPlayer(f)?.let { teamPlayers.add(it) }
        getPlayer(g)?.let { teamPlayers.add(it) }
        getPlayer(g)?.let { teamPlayers.add(it) }

        //add players to the playersInTeam list
        playersInTeam.addAll(teamPlayers)

        return teamPlayers
    }

    private fun getPlayer(position: String): Player? {
        val ratingsOrder = mutableListOf(1, 1, 2, 2, 3, 3, 4, 4, 5)
        val player: Player? = when (position) {
            "g" -> {
                var rating: Int
                do {
                    rating = ratingsOrder.random()
                } while (
                    (rating == 1 && guards49_60.isEmpty()) ||
                    (rating == 2 && guards61_66.isEmpty()) ||
                    (rating == 3 && guards67_70.isEmpty()) ||
                    (rating == 4 && guards71_90.isEmpty()) ||
                    (rating == 5 && guards91_max.isEmpty())
                )
                ratingsOrder.remove(rating)
                when (rating) {
                    1 -> guards49_60.removeAt(guards49_60.indices.random())
                    2 -> guards61_66.removeAt(guards61_66.indices.random())
                    3 -> guards67_70.removeAt(guards67_70.indices.random())
                    4 -> guards71_90.removeAt(guards71_90.indices.random())
                    else -> guards91_max.removeAt(guards91_max.indices.random())
                }
            }

            "f" -> {
                var rating: Int
                do {
                    rating = ratingsOrder.random()
                } while (
                    (rating == 1 && forwards49_60.isEmpty()) ||
                    (rating == 2 && forwards61_66.isEmpty()) ||
                    (rating == 3 && forwards67_70.isEmpty()) ||
                    (rating == 4 && forwards71_90.isEmpty()) ||
                    (rating == 5 && forwards91_max.isEmpty())
                )
                ratingsOrder.remove(rating)
                when (rating) {
                    1 -> forwards49_60.removeAt(forwards49_60.indices.random())
                    2 -> forwards61_66.removeAt(forwards61_66.indices.random())
                    3 -> forwards67_70.removeAt(forwards67_70.indices.random())
                    4 -> forwards71_90.removeAt(forwards71_90.indices.random())
                    else -> forwards91_max.removeAt(forwards91_max.indices.random())
                }
            }

            "c" -> {
                var rating: Int
                do {
                    rating = ratingsOrder.random()
                } while (
                    (rating == 1 && centers49_60.isEmpty()) ||
                    (rating == 2 && centers61_66.isEmpty()) ||
                    (rating == 3 && centers67_70.isEmpty()) ||
                    (rating == 4 && centers71_90.isEmpty()) ||
                    (rating == 5 && centers91_max.isEmpty())
                )
                ratingsOrder.remove(rating)
                when (rating) {
                    1 -> centers49_60.removeAt(centers49_60.indices.random())
                    2 -> centers61_66.removeAt(centers61_66.indices.random())
                    3 -> centers67_70.removeAt(centers67_70.indices.random())
                    4 -> centers71_90.removeAt(centers71_90.indices.random())
                    else -> centers91_max.removeAt(centers91_max.indices.random())
                }
            }

            else -> null
        }
        return player
    }

    private fun starters(players: List<Player>): Map<String, Player?> {
        val starters = mutableMapOf<String, Player?>()

        starters["PG"] = players.filter { it.position1 == "Guard" }.maxByOrNull { it.rating }
        starters["SG"] = players.filter { it.position1 == "Guard" }.filter { it != starters["PG"] }
            .maxByOrNull { it.rating }
        starters["SF"] = players.filter { it.position1 == "Forward" }.maxByOrNull { it.rating }
        starters["PF"] =
            players.filter { it.position1 == "Forward" }.filter { it != starters["SF"] }
                .maxByOrNull { it.rating }
        starters["C"] = players.filter { it.position1 == "Center" }.maxByOrNull { it.rating }

        return starters
    }

    private fun bench(starters: Map<String, Player?>, players: List<Player>): List<Player> {
        return players.filter { it !in starters.values }
    }


    private fun getFreeAgencyPlayers(players: List<Player>): List<Player> {
        val playersInFreeAgency = mutableListOf<Player>()

        players.forEach { player ->
            if (player !in playersInTeam) {
                playersInFreeAgency.add(player)
            }
        }
        return playersInFreeAgency
    }


    private suspend fun parseTeamCsvFile(
        context: Context,
    ): List<Team> =
        withContext(Dispatchers.IO) {

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

                    val pl = assingPlayersToTeam()
                    val starters = starters(pl)
                    val bench = bench(starters, pl)
                    // Create a new Team instance with the parsed data and add it to the list
                    val team = Team(
                        name = name,
                        abbreviation = abbreviation,
                        conference = conference,
                        division = division,
                        logo = logo,
                        arena = arena,
                        players = pl,
                        positions = starters,
                        bench = bench,
                        gameIds = mutableListOf(),
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


    private fun divideTeams(teams: List<Team>) {
        for (team in teams) {
            when (team.conference) {
                "East" -> easternConference.add(team)
                "West" -> westernConference.add(team)
            }

            when (team.division) {
                "Atlantic" -> atlanticDivision.add(team)
                "Central" -> centralDivision.add(team)
                "Southeast" -> southeastDivision.add(team)
                "Northwest" -> northwestDivision.add(team)
                "Pacific" -> pacificDivision.add(team)
                "Southwest" -> southwestDivision.add(team)
            }
        }


    }


    private fun generateSchedule(): List<Game> {

        val divisions = listOf(
            atlanticDivision,
            centralDivision,
            southeastDivision,
            northwestDivision,
            pacificDivision,
            southwestDivision
        )

        val thegames = mutableListOf<Game>()

        divisions.forEach { division ->
            thegames.addAll(genereteDivisionGames(division))
        }

        //each team will play 4 games against 6 teams in their conference but not in their division (4*6=24 games)
        //each team will play 3 games against the remaining 4 teams in their conference (3*4=12 games)
        val conferenceDivisions = listOf(
            Pair(easternConference, listOf(atlanticDivision, centralDivision, southeastDivision)),
            Pair(westernConference, listOf(northwestDivision, pacificDivision, southwestDivision))
        )

        conferenceDivisions.forEach { (conference, divisions) ->
            thegames.addAll(generateGamesFromConferenceOutOfDivision(conference, divisions))
        }

        //each team will play 2 games against each team in the opposing conference (2*15=30 games)
        //create games between conferences
        thegames.addAll(generateGamesBetweenConferences())

        return thegames
    }

    private fun genereteDivisionGames(division: List<Team>): List<Game> {


        val divisionGames = mutableListOf<Game>()
        //repeat for each team in the division 2 times
        division.forEach { team ->
            for (i in 1..2) {
                //repeat for each team in the division
                division.forEach { opponent ->
                    if (team != opponent) {
                        val game = createGame(team, opponent)
                        divisionGames.add(game)
                    }
                }
            }
        }
        return divisionGames
    }

    private fun generateGamesFromConferenceOutOfDivision(
        conference: List<Team>,
        divisions: List<List<Team>>,

        ): List<Game> {
        //each team will play 4 games against 6 teams in their conference but not in their division (4*6=24 games)
        //each team will play 3 games against the remaining 4 teams in their conference (3*4=12 games)

        val allGames = mutableListOf<Game>()

        // each team will play at least 2 games against each team in the same conference (2*15=30 games)
        conference.forEach { team ->
            //repeat for each team in the conference
            conference.forEach { opponent ->
                if (team != opponent) {
                    val game1 = createGame(team, opponent)
                    val game2 = createGame(opponent, team)
                    allGames += listOf(game1, game2)
                }
            }
        }


        //create a map with all the teams from the conference and the number of games they are scechule
        val conferenceTeams = mutableMapOf<Team, Int>()

        //add all the teams from the conference to the map
        conference.forEach { team ->
            conferenceTeams[team] = 0
        }

        val division1 = divisions[0]
        val division2 = divisions[1]
        val division3 = divisions[2]



        division1.forEach { team ->

            //list of teams from the conference that the team has played
            val playedTeams = mutableListOf<Team>()

            // choose a random team from the division2 while the opponent has less than 4 games scheduled
            repeat(3) {
                var opponent = division2.random()
                while (opponent in playedTeams) {
                    opponent = division2.random()
                }
                val game1 = createGame(team, opponent)
                val game2 = createGame(opponent, team)
                allGames += listOf(game1, game2)
                playedTeams += opponent
            }

            //play the rest of the division2 teams 1 time
            division2.filterNot { it in playedTeams }.forEach { opponent ->
                val random = (0..1).random()
                val game = if (random == 0) createGame(team, opponent) else createGame(
                    opponent,
                    team
                )
                allGames.add(game)
            }


            // choose a random team from the division3 while the opponent has less than 4 games scheduled
            repeat(3) {
                var opponent = division3.random()
                while (opponent in playedTeams) {
                    opponent = division3.random()
                }
                val game1 = createGame(team, opponent)
                val game2 = createGame(opponent, team)
                allGames += listOf(game1, game2)
                playedTeams += opponent
            }

            //play the rest of the division2 teams 1 time
            division3.filterNot { it in playedTeams }.forEach { opponent ->
                val random = (0..1).random()
                val game = if (random == 0) createGame(team, opponent) else createGame(
                    opponent,
                    team
                )
                allGames.add(game)
            }
        }

        val p2gvsdiv2Team = mutableListOf<Team>()

        division2.forEach { team ->
            var opponent = division3.random()
            while (opponent in p2gvsdiv2Team) {
                opponent = division3.random()
            }
            val game1 = createGame(team, opponent)
            val game2 = createGame(opponent, team)
            allGames += listOf(game1, game2)
            p2gvsdiv2Team += opponent

            division3.filterNot { it == opponent }.forEach { team3 ->
                val random = (0..1).random()
                val game = if (random == 0) createGame(team, team3) else createGame(
                    team3,
                    team
                )
                allGames.add(game)
            }
        }




        return allGames
    }

    private fun generateGamesBetweenConferences(): List<Game> {


        val allGames = mutableListOf<Game>()
        //repeat for each team in the east
        easternConference.forEach { team ->
            //repeat for each team in the west
            westernConference.forEach { opponent ->
                val game1 = createGame(team, opponent)
                val game2 = createGame(opponent, team)
                allGames += listOf(game1, game2)
            }
        }

        return allGames
    }

    private fun createGame(homeTeam: Team, awayTeam: Team): Game {
        return Game(
            season = 1,
            arena = homeTeam.arena,
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            homeScore = 0,
            awayScore = 0,
            homeStarters = null,
            awayStarters = null,
            homeBenchedPlayers = null,
            awayBenchedPlayers = null,
            winner = null,
            loser = null
        )
    }

    private fun createSeason(teams: List<Team>, players: List<Player>, games: List<Game>?): Season {
        return Season(
            id = 1,
            teams = teams,
            players = players,
            tradeList = getFreeAgencyPlayers(players),
            schedule = emptyList(),
            standings = null,
            playoffs = null,
            currentRound = 1
        )
    }

    private suspend fun addGamesToTeams(teams: List<Team>, gameDao: GameDao): List<Team> {
        teams.forEach { team ->
            gameDao.getAllGames().single().forEach {game ->
                if (game.homeTeam == team || game.awayTeam == team) {
                    team.gameIds += game.id.toString()
                }
            }

        }

        return teams
    }

    private fun generateShortSchedule(teams: List<Team>): List<Game>{
        var shortSchedule = mutableListOf<Game>()
        teams.forEach { team ->
            teams.forEach { opponent ->
                if (team != opponent) {
                    val game1 = createGame(team, opponent)
                    shortSchedule += game1
                }
            }
        }
        return shortSchedule
    }

    fun populateDatabase(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val database = getDatabase(context)

            val players = parsePlayerCsvFile(context)
            dividePlayersByRating(players)
            val teams = parseTeamCsvFile(context)
            //divideTeams(teams)
            val season = createSeason(teams, players, null)

            database.playerDao().insertAllPlayers(players)
            database.teamDao().insertAllTeams(teams)
            database.seasonDao().insertSeason(season)

            val gamesList = generateShortSchedule(teams) // Generate games for the teams
            database.gameDao().insertAllGames(gamesList) // Insert games into the database

           /* val teamsWithGames = addGamesToTeams(teams, database.gameDao()) // Add the games to the teams

            teamsWithGames.forEach { team ->
                database.teamDao().updateTeam(team)
            }*/

        }
    }


}
