package com.dam.hoopsdynasty.data.dao

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
    fun getAllGames(): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGame(id: Int): Flow<Game>

    @Query("SELECT * FROM games WHERE season = :season")
    fun getGamesBySeason(season: Int): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE homeTeam = :team OR awayTeam = :team")
    fun getGamesByTeam(team: Team): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE homeTeam = :team")
    fun getHomeGamesByTeam(team: String): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE awayTeam = :team")
    fun getAwayGamesByTeam(team: String): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE winner = :team")
    fun getGamesByWinner(team: String): Flow<List<Game>>

    @Query("SELECT * FROM games WHERE loser = :team")
    fun getGamesByLoser(team: String): Flow<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Insert
    suspend fun insertAllGames(games: List<Game>)

    @Update
    suspend fun updateGame(game: Game)
}