package com.dam.hoopsdynasty.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar


@Entity(tableName = "teams")
data class Team(
    @PrimaryKey val abbreviation: String,
    val name: String,
    val conference: String,
    val division: String,
    val logo: String,
    val arena: String,
    var players: List<Player>?,
    var bench: List<Player>?,
    var positions: Map<String, Player?> = mapOf(
        "PG" to null,
        "SG" to null,
        "SF" to null,
        "PF" to null,
        "C" to null
    ),
    var schedule: List<Game>,
    var gamesPlayed: List<Game>,
    var wins: Int = 0,
    var losses: Int = 0

)