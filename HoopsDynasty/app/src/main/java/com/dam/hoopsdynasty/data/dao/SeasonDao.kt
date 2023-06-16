package com.dam.hoopsdynasty.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Season
import kotlinx.coroutines.flow.Flow

import androidx.lifecycle.LiveData

@Dao
interface SeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeason(season: Season)

    @Query("SELECT * FROM seasons LIMIT 1")
    fun getSeason(): LiveData<Season?>

    @Update
    suspend fun updateSeason(season: Season)
}
