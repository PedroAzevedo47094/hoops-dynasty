package com.dam.hoopsdynasty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "seasons")
data class Season(
    @PrimaryKey val id: Int = 1,
    val teams: List<Team>?,
    val players: List<Player>?,
    val tradeList: List<Player>,
    val schedule: List<Game>?,
    val standings: String?,
    val playoffs: String?,
    val currentRound: Int?,
)
