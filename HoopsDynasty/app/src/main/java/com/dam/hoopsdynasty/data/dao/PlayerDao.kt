package com.dam.hoopsdynasty.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players")
    fun getAllPlayers(): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE id = :id")
    fun getPlayer(id: Int): Flow<Player>

    @Query("SELECT * FROM players WHERE position1 = :position OR position2 = :position")
    fun getPlayersByPosition(position: String): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating")
    fun getPlayersByRating(rating: Float): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating AND (position1 = :position OR position2 = :position)")
    fun getPlayersByRatingAndPosition(rating: Float, position: String): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating AND rating <= :rating2")
    fun getPlayersByRatingRange(rating: Float, rating2: Float): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating AND rating <= :rating2 AND (position1 = :position OR position2 = :position)")
    fun getPlayersByRatingRangeAndPosition(
        rating: Float,
        rating2: Float,
        position: String
    ): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE team = :team")
    fun getPlayersByTeam(team: String): Flow<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun instertPlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlayers(players: List<Player>)
}
