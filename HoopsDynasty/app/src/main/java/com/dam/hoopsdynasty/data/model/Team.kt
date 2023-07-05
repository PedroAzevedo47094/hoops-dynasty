package com.dam.hoopsdynasty.data.model


import androidx.compose.runtime.MutableState
import androidx.room.Entity
import androidx.room.PrimaryKey


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
    var games: List<Int>?, // Store the IDs of the games associated with the team
    var wins: Int = 0,
    var losses: Int = 0
)