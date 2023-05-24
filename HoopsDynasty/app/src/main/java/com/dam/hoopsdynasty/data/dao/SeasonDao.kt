package com.dam.hoopsdynasty.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Season

@Dao
interface SeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeason(season: Season)

    @Query("SELECT * FROM seasons LIMIT 1")
    suspend fun getSeason(): Season?

    @Update
    suspend fun updateSeason(season: Season)

}