package com.dam.hoopsdynasty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "managers")
data class Manager(
    @PrimaryKey val id: Int = 1,
    val uid: String,
    val email: String,
    val name:String,
    val password: String,
    var team: Team?
)
