package com.dam.hoopsdynasty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Set a default value of 0 to enable auto-increment
    val season: Int,
    val arena: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeScore: Int,
    val awayScore: Int,
    val homeStarters: List<Player>?,
    val awayStarters: List<Player>?,
    val homeBenchedPlayers: List<Player>?,
    val awayBenchedPlayers: List<Player>?,
    val winner: Team?,
    val loser: Team?
)
