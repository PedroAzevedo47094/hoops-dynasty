package com.dam.hoopsdynasty.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    fun getAllGames(): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGame(id: Int): LiveData<Game>

    @Query("SELECT * FROM games WHERE season = :season")
    fun getGamesBySeason(season: Int): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE homeTeamId = :team OR awayTeamId = :team")
    fun getGamesByTeam(team: String): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE homeTeamId = :team")
    fun getHomeGamesByTeam(team: String): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE awayTeamId = :team")
    fun getAwayGamesByTeam(team: String): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE winner = :team")
    fun getGamesByWinner(team: String): LiveData<List<Game>>

    @Query("SELECT * FROM games WHERE loser = :team")
    fun getGamesByLoser(team: String): LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Insert
    suspend fun insertAllGames(games: List<Game>)

    @Update
    suspend fun updateGame(game: Game)


}