package com.dam.hoopsdynasty.data

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team

class GamesTypeAdapter(private val gson: Gson) : TypeAdapter<List<Game>>() {
    override fun write(out: JsonWriter, value: List<Game>?) {
        if (value == null) {
            out.nullValue()
            return
        }

        out.beginArray()
        value.forEach { game ->
            out.beginObject()
            out.name("id").value(game.id)
            out.name("season").value(game.season)
            out.name("arena").value(game.arena)
            out.name("homeTeam").value(game.homeTeam.abbreviation)
            out.name("awayTeam").value(game.awayTeam.abbreviation)
            out.name("homeScore").value(game.homeScore)
            out.name("awayScore").value(game.awayScore)
            // Serialize other properties as required
            out.endObject()
        }
        out.endArray()
    }

    override fun read(`in`: JsonReader): List<Game>? {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return null
        }

        val games = mutableListOf<Game>()
        `in`.beginArray()
        while (`in`.hasNext()) {
            `in`.beginObject()
            var id = 0
            var season = 0
            var arena = ""
            var homeTeam: Team? = null
            var awayTeam: Team? = null
            var homeScore = 0
            var awayScore = 0
            // Read other properties as required
            while (`in`.hasNext()) {
                when (`in`.nextName()) {
                    "id" -> id = `in`.nextInt()
                    "season" -> season = `in`.nextInt()
                    "arena" -> arena = `in`.nextString()
                    "homeTeam" -> homeTeam = gson.fromJson(`in`, Team::class.java)
                    "awayTeam" -> awayTeam = gson.fromJson(`in`, Team::class.java)
                    "homeScore" -> homeScore = `in`.nextInt()
                    "awayScore" -> awayScore = `in`.nextInt()
                    // Read other properties as required
                }
            }
            `in`.endObject()

            val game = Game(
                id = id,
                season = season,
                arena = arena,
                homeTeam = homeTeam ?: Team("", "", "", "", "", "", null, null, emptyMap(), emptyList(), 0, 0),
                awayTeam = awayTeam ?: Team("", "", "", "", "", "", null, null, emptyMap(), emptyList(), 0, 0),
                homeScore = homeScore,
                awayScore = awayScore,
                homeStarters = null, // Set the appropriate values
                awayStarters = null, // Set the appropriate values
                homeBenchedPlayers = null, // Set the appropriate values
                awayBenchedPlayers = null, // Set the appropriate values
                winner = null, // Set the appropriate values
                loser = null // Set the appropriate values
            )

            games.add(game)
        }
        `in`.endArray()

        return games
    }
}
