package com.dam.hoopsdynasty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey val id: Int, // Set a default value of 0 to enable auto-increment
    val season: Int,
    val arena: String,
    val homeTeamId: String,
    val awayTeamId: String,
    var homeScore: Int,
    var awayScore: Int,
    val homeStarters: List<Player>?,
    val awayStarters: List<Player>?,
    val homeBenchedPlayers: List<Player>?,
    val awayBenchedPlayers: List<Player>?,
    var winner: String?,
    var loser: String?
)
