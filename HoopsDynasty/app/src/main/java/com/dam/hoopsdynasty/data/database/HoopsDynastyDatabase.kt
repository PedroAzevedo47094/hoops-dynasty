package com.dam.hoopsdynasty.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dam.hoopsdynasty.data.Converters
import com.dam.hoopsdynasty.data.dao.GameDao
import com.dam.hoopsdynasty.data.dao.ManagerDao
import com.dam.hoopsdynasty.data.dao.PlayerDao
import com.dam.hoopsdynasty.data.dao.SeasonDao
import com.dam.hoopsdynasty.data.dao.TeamDao
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Season
import com.dam.hoopsdynasty.data.model.Team

@Database(
    entities = [Team::class, Player::class, Manager::class, Season::class, Game::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HoopsDynastyDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
    abstract fun managerDao(): ManagerDao
    abstract fun seasonDao(): SeasonDao
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: HoopsDynastyDatabase? = null

        fun getDatabase(context: Context): HoopsDynastyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HoopsDynastyDatabase::class.java,
                    "hoops_dynasty_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }
}
