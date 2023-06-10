package com.dam.hoopsdynasty.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface ManagerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManager(manager: Manager)

    @Query("SELECT * FROM managers LIMIT 1")
    fun getManager(): Flow<Manager?>

    @Update
    suspend fun updateManager(manager: Manager)

}
