package com.dam.hoopsdynasty.data.repository

import com.dam.hoopsdynasty.data.dao.SeasonDao
import com.dam.hoopsdynasty.data.model.Season

class SeasonRepository(private val seasonDao: SeasonDao) {

    //insert season
    suspend fun insertSeason(season: Season) {
        seasonDao.insertSeason(season)
    }

    //get season
    suspend fun getSeason(): Season? {
        return seasonDao.getSeason()
    }

    //update season
    suspend fun updateSeason(season: Season) {
        seasonDao.updateSeason(season)
    }

    //singleton pattern
    companion object {
        @Volatile
        private var instance: SeasonRepository? = null

        fun getInstance(seasonDao: SeasonDao): SeasonRepository {
            return instance ?: synchronized(this) {
                instance ?: SeasonRepository(seasonDao).also { instance = it }
            }
        }
    }
}
