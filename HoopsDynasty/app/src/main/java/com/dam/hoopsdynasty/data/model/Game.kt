package com.dam.hoopsdynasty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class Game(
    @PrimaryKey val id: Int,
    val season: Int,
    val arena: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val homeScore: Int,
    val awayScore: Int,
    val homeStarters: List<Player>,
    val awayStarters: List<Player>,
    val homeBenchedPlayers: List<Player>,
    val awayBenchedPlayers: List<Player>,
    val homeStats: List<Float>,
    val awayStats: List<Float>,
    val homeTeamStats: Map<String, Float>,
    val awayTeamStats: Map<String, Float>,
    val winner: Team,
    val loser: Team
)
