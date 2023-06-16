package com.dam.hoopsdynasty.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey val id: Int,
    val image: String,
    val firstName: String,
    val lastName: String,
    val position1: String,
    val position2: String,
    val rating: Float,
    val ppg: Float,
    val rpg: Float,
    val apg: Float,
    val bpg: Float,
    val spg: Float,
    val topg: Float,
    val orepg: Float,
    val drebpg: Float,
    val fga: Float,
    val fgm: Float,
    val fta: Float,
    val ftm: Float,
    val tpgm: Float,
)
