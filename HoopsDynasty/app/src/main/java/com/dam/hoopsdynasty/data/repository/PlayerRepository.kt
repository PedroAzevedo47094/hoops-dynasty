package com.dam.hoopsdynasty.data.repository

import com.dam.hoopsdynasty.data.dao.PlayerDao
import com.dam.hoopsdynasty.data.model.Player
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData

class PlayerRepository(private val playerDao: PlayerDao) {

    val players: LiveData<List<Player>> = playerDao.getAllPlayers()

    fun getAllPlayers(): LiveData<List<Player>> {
            return playerDao.getAllPlayers()
        }

    fun getPlayer(id: Int): LiveData<Player> {
        return playerDao.getPlayer(id)
    }

    fun getPlayersByPosition(position: String): LiveData<List<Player>> {
        return playerDao.getPlayersByPosition(position)
    }

    fun getPlayersByRating(rating: Float): LiveData<List<Player>> {
        return playerDao.getPlayersByRating(rating)
    }

    fun getPlayersByRatingAndPosition(rating: Float, position: String): LiveData<List<Player>> {
        return playerDao.getPlayersByRatingAndPosition(rating, position)
    }

    fun getPlayersByRatingRange(rating: Float, rating2: Float): LiveData<List<Player>> {
        return playerDao.getPlayersByRatingRange(rating, rating2)
    }

    fun getPlayersByRatingRangeAndPosition(
        rating: Float,
        rating2: Float,
        position: String
    ): LiveData<List<Player>> {
        return playerDao.getPlayersByRatingRangeAndPosition(rating, rating2, position)
    }


    suspend fun insertPlayer(player: Player) {
        playerDao.insertPlayer(player)
    }

    suspend fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player)
    }

    suspend fun insertAllPlayers(players: List<Player>) {
        playerDao.insertAllPlayers(players)
    }
}
