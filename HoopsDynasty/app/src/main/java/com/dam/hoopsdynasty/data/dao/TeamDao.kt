package com.dam.hoopsdynasty.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.flow.Flow

import androidx.lifecycle.LiveData

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams")
    fun getAllTeams(): LiveData<List<Team>>

    @Query("SELECT * FROM teams WHERE abbreviation = :abbreviation")
    fun getTeam(abbreviation: String?): LiveData<Team>

    @Query("SELECT * FROM teams WHERE conference = :conference")
    fun getTeamsByConference(conference: String): LiveData<List<Team>>

    @Query("SELECT * FROM teams WHERE division = :division")
    fun getTeamsByDivision(division: String): LiveData<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Update
    suspend fun updateTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTeams(teams: List<Team>)
}
