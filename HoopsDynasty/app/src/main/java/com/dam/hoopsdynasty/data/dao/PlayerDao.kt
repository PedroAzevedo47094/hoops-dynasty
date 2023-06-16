package com.dam.hoopsdynasty.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Player

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players")
    fun getAllPlayers(): LiveData<List<Player>>

    @Query("SELECT * FROM players WHERE id = :id")
    fun getPlayer(id: Int): LiveData<Player>

    @Query("SELECT * FROM players WHERE position1 = :position OR position2 = :position")
    fun getPlayersByPosition(position: String): LiveData<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating")
    fun getPlayersByRating(rating: Float): LiveData<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating AND (position1 = :position OR position2 = :position)")
    fun getPlayersByRatingAndPosition(rating: Float, position: String): LiveData<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating AND rating <= :rating2")
    fun getPlayersByRatingRange(rating: Float, rating2: Float): LiveData<List<Player>>

    @Query("SELECT * FROM players WHERE rating >= :rating AND rating <= :rating2 AND (position1 = :position OR position2 = :position)")
    fun getPlayersByRatingRangeAndPosition(
        rating: Float,
        rating2: Float,
        position: String
    ): LiveData<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlayers(players: List<Player>)
}
