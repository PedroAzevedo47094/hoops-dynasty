package com.dam.hoopsdynasty.data

import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Season
import com.dam.hoopsdynasty.data.model.Team
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class ManagerAdapter : JsonSerializer<Manager>, JsonDeserializer<Manager> {
    override fun serialize(src: Manager?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        src?.let { manager ->
            jsonObject.addProperty("id", manager.id)
            jsonObject.addProperty("uid", manager.uid)
            jsonObject.addProperty("email", manager.email)
            jsonObject.addProperty("name", manager.name)
            jsonObject.addProperty("password", manager.password)
            jsonObject.add("team", context?.serialize(manager.team))
        }
        return jsonObject
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Manager {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asInt ?: 0
        val uid = jsonObject?.get("uid")?.asString ?: ""
        val email = jsonObject?.get("email")?.asString ?: ""
        val name = jsonObject?.get("name")?.asString ?: ""
        val password = jsonObject?.get("password")?.asString ?: ""
        val team = context?.deserialize<Team>(jsonObject?.get("team"), Team::class.java)

        return Manager(
            id= id,
            uid = uid,
            email = email,
            name = name,
            password = password,
            team = team)
    }
}
class TeamAdapter : JsonSerializer<Team>, JsonDeserializer<Team> {
    override fun serialize(src: Team?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        src?.let { team ->
            jsonObject.addProperty("abbreviation", team.abbreviation)
            jsonObject.addProperty("name", team.name)
            jsonObject.addProperty("conference", team.conference)
            jsonObject.addProperty("division", team.division)
            jsonObject.addProperty("logo", team.logo)
            jsonObject.addProperty("arena", team.arena)
            jsonObject.add("players", context?.serialize(team.players))
            jsonObject.add("bench", context?.serialize(team.bench))
            jsonObject.add("positions", context?.serialize(team.positions))
            jsonObject.add("games", context?.serialize(team.games))
            jsonObject.addProperty("wins", team.wins)
            jsonObject.addProperty("losses", team.losses)
        }
        return jsonObject
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Team {
        val jsonObject = json?.asJsonObject
        val abbreviation = jsonObject?.get("abbreviation")?.asString ?: ""
        val name = jsonObject?.get("name")?.asString ?: ""
        val conference = jsonObject?.get("conference")?.asString ?: ""
        val division = jsonObject?.get("division")?.asString ?: ""
        val logo = jsonObject?.get("logo")?.asString ?: ""
        val arena = jsonObject?.get("arena")?.asString ?: ""
        val players = context?.deserialize<List<Player>>(jsonObject?.get("players"), object : TypeToken<List<Player>>() {}.type)
        val bench = context?.deserialize<List<Player>>(jsonObject?.get("bench"), object : TypeToken<List<Player>>() {}.type)
        val positions = context?.deserialize<Map<String, Player?>>(jsonObject?.get("positions"), object : TypeToken<Map<String, Player?>>() {}.type)
        val games = context?.deserialize<List<Game>>(jsonObject?.get("games"), object : TypeToken<List<Game>>() {}.type)
        val wins = jsonObject?.get("wins")?.asInt ?: 0
        val losses = jsonObject?.get("losses")?.asInt ?: 0

        val team = Team(
            name = name,
            abbreviation = abbreviation,
            conference = conference,
            division = division,
            logo = logo,
            arena = arena,
            players = players,
            positions = positions!!,
            bench = bench,
            games = games,
            wins = wins,
            losses = losses
        )

        return team
    }
}
class PlayerAdapter : JsonSerializer<Player>, JsonDeserializer<Player> {
    override fun serialize(src: Player?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        src?.let { player ->
            jsonObject.addProperty("id", player.id)
            jsonObject.addProperty("image", player.image)
            jsonObject.addProperty("firstName", player.firstName)
            jsonObject.addProperty("lastName", player.lastName)
            jsonObject.addProperty("position1", player.position1)
            jsonObject.addProperty("position2", player.position2)
            jsonObject.addProperty("rating", player.rating)
            jsonObject.addProperty("ppg", player.ppg)
            jsonObject.addProperty("rpg", player.rpg)
            jsonObject.addProperty("apg", player.apg)
            jsonObject.addProperty("bpg", player.bpg)
            jsonObject.addProperty("spg", player.spg)
            jsonObject.addProperty("topg", player.topg)
            jsonObject.addProperty("orepg", player.orepg)
            jsonObject.addProperty("drebpg", player.drebpg)
            jsonObject.addProperty("fga", player.fga)
            jsonObject.addProperty("fgm", player.fgm)
            jsonObject.addProperty("fta", player.fta)
            jsonObject.addProperty("ftm", player.ftm)
            jsonObject.addProperty("tpgm", player.tpgm)
        }
        return jsonObject
    }


    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Player {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asInt ?: 0
        val image = jsonObject?.get("image")?.asString ?: ""
        val firstName = jsonObject?.get("firstName")?.asString ?: ""
        val lastName = jsonObject?.get("lastName")?.asString ?: ""
        val position1 = jsonObject?.get("position1")?.asString ?: ""
        val position2 = jsonObject?.get("position2")?.asString ?: ""
        val rating = jsonObject?.get("rating")?.asFloat ?: 0f
        val ppg = jsonObject?.get("ppg")?.asFloat ?: 0f
        val rpg = jsonObject?.get("rpg")?.asFloat ?: 0f
        val apg = jsonObject?.get("apg")?.asFloat ?: 0f
        val bpg = jsonObject?.get("bpg")?.asFloat ?: 0f
        val spg = jsonObject?.get("spg")?.asFloat ?: 0f
        val topg = jsonObject?.get("topg")?.asFloat ?: 0f
        val orepg = jsonObject?.get("orepg")?.asFloat ?: 0f
        val drebpg = jsonObject?.get("drebpg")?.asFloat ?: 0f
        val fga = jsonObject?.get("fga")?.asFloat ?: 0f
        val fgm = jsonObject?.get("fgm")?.asFloat ?: 0f
        val fta = jsonObject?.get("fta")?.asFloat ?: 0f
        val ftm = jsonObject?.get("ftm")?.asFloat ?: 0f
        val tpgm = jsonObject?.get("tpgm")?.asFloat ?: 0f


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
        )

        return player
    }
}
class GameAdapter : JsonSerializer<Game>, JsonDeserializer<Game> {
    override fun serialize(src: Game?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        src?.let { game ->
            jsonObject.addProperty("id", game.id)
            jsonObject.addProperty("season", game.season)
            jsonObject.addProperty("arena", game.arena)
            jsonObject.addProperty("homeTeamId", game.homeTeamId)
            jsonObject.addProperty("awayTeamId", game.awayTeamId)
            jsonObject.addProperty("homeScore", game.homeScore)
            jsonObject.addProperty("awayScore", game.awayScore)
            jsonObject.add("homeStarters", context?.serialize(game.homeStarters))
            jsonObject.add("awayStarters", context?.serialize(game.awayStarters))
            jsonObject.add("homeBenchedPlayers", context?.serialize(game.homeBenchedPlayers))
            jsonObject.add("awayBenchedPlayers", context?.serialize(game.awayBenchedPlayers))
            jsonObject.add("winner", context?.serialize(game.winner))
            jsonObject.add("loser", context?.serialize(game.loser))
        }
        return jsonObject
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Game {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asInt ?: 0
        val season = jsonObject?.get("season")?.asInt ?: 0
        val arena = jsonObject?.get("arena")?.asString ?: ""
        val homeTeamId = jsonObject?.get("homeTeamId")?.asString ?: ""
        val awayTeamId = jsonObject?.get("awayTeamId")?.asString ?: ""
        val homeScore = jsonObject?.get("homeScore")?.asInt ?: 0
        val awayScore = jsonObject?.get("awayScore")?.asInt ?: 0
        val homeStarters = context?.deserialize<List<Player>>(jsonObject?.get("homeStarters"), object : TypeToken<List<Player>>() {}.type)
        val awayStarters = context?.deserialize<List<Player>>(jsonObject?.get("awayStarters"), object : TypeToken<List<Player>>() {}.type)
        val homeBenchedPlayers = context?.deserialize<List<Player>>(jsonObject?.get("homeBenchedPlayers"), object : TypeToken<List<Player>>() {}.type)
        val awayBenchedPlayers = context?.deserialize<List<Player>>(jsonObject?.get("awayBenchedPlayers"), object : TypeToken<List<Player>>() {}.type)
        val winner = context?.deserialize<String>(jsonObject?.get("winner"), String::class.java)
        val loser = context?.deserialize<String>(jsonObject?.get("loser"), String::class.java)

        return Game(
            id = id,
            season = season,
            arena = arena,
            homeTeamId = homeTeamId,
            awayTeamId = awayTeamId,
            homeScore = homeScore,
            awayScore = awayScore,
            homeStarters = homeStarters,
            awayStarters = awayStarters,
            homeBenchedPlayers = homeBenchedPlayers,
            awayBenchedPlayers = awayBenchedPlayers,
            winner = winner,
            loser = loser
        )
    }

}

class SeasonAdapter : JsonSerializer<Season>, JsonDeserializer<Season> {
    override fun serialize(src: Season?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()
        src?.let { season ->
            jsonObject.addProperty("id", season.id)
            jsonObject.add("teams", context?.serialize(null))
            jsonObject.add("players", context?.serialize(null))
            jsonObject.add("tradeList", context?.serialize(season.tradeList))
            jsonObject.add("schedule", context?.serialize(null))
            jsonObject.add("standings", context?.serialize(null))
            jsonObject.add("playoffs", context?.serialize(null))
            jsonObject.add("currentRound", context?.serialize(null))
        }
        return jsonObject
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Season {
        val jsonObject = json?.asJsonObject
        val id = jsonObject?.get("id")?.asInt ?: 0
        val teams = context?.deserialize<List<Team>>(jsonObject?.get("teams"), object : TypeToken<List<Team>>() {}.type)
        val players = context?.deserialize<List<Player>>(jsonObject?.get("players"), object : TypeToken<List<Player>>() {}.type)
        val tradeList = context?.deserialize<List<Player>>(jsonObject?.get("tradeList"), object : TypeToken<List<Player>>() {}.type)
        val schedule = context?.deserialize<List<Game>>(jsonObject?.get("schedule"), object : TypeToken<List<Game>>() {}.type)
        val standings = jsonObject?.get("standings")?.asString ?: ""
        val playoffs = jsonObject?.get("playoffs")?.asString ?: ""
        val currentRound = jsonObject?.get("currentRound")?.asInt ?: 0


        return Season(
            id = id,
            teams = teams,
            players = players,
            tradeList = tradeList!!,
            schedule = schedule,
            standings = standings,
            playoffs = playoffs,
            currentRound = currentRound

        )
    }
}

