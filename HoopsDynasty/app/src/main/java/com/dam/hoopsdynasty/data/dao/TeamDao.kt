package com.dam.hoopsdynasty.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Query("SELECT * FROM teams")
    fun getAllTeams(): Flow<List<Team>>

    @Query("SELECT * FROM teams WHERE abbreviation = :abbreviation")
    fun getTeam(abbreviation: String): Flow<Team>

    @Query("SELECT * FROM teams WHERE conference = :conference")
    fun getTeamsByConference(conference: String): Flow<List<Team>>

    @Query("SELECT * FROM teams WHERE division = :division")
    fun getTeamsByDivision(division: String): Flow<List<Team>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Update
    suspend fun updateTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTeams(teams: List<Team>)
}
