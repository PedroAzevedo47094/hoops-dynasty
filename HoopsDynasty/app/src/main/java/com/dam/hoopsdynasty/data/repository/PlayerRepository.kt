package com.dam.hoopsdynasty.data.repository

import com.dam.hoopsdynasty.data.dao.PlayerDao
import com.dam.hoopsdynasty.data.model.Player
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {

    val players = playerDao.getAllPlayers()

    fun getPlayer(id: Int): Flow<Player> {
        return playerDao.getPlayer(id)
    }

    fun getPlayersByPosition(position: String): Flow<List<Player>> {
        return playerDao.getPlayersByPosition(position)
    }

    fun getPlayersByRating(rating: Float): Flow<List<Player>> {
        return playerDao.getPlayersByRating(rating)
    }

    fun getPlayersByRatingAndPosition(rating: Float, position: String): Flow<List<Player>> {
        return playerDao.getPlayersByRatingAndPosition(rating, position)
    }

    fun getPlayersByRatingRange(rating: Float, rating2: Float): Flow<List<Player>> {
        return playerDao.getPlayersByRatingRange(rating, rating2)
    }

    fun getPlayersByRatingRangeAndPosition(
        rating: Float,
        rating2: Float,
        position: String
    ): Flow<List<Player>> {
        return playerDao.getPlayersByRatingRangeAndPosition(rating, rating2, position)
    }

    fun getPlayersByTeam(teamAbbreviation: String): Flow<List<Player>> {
        return playerDao.getPlayersByTeam(teamAbbreviation)
    }

    suspend fun insertPlayer(player: Player) {
        playerDao.instertPlayer(player)
    }

    suspend fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player)
    }

    suspend fun insertAllPlayers(players: List<Player>) {
        playerDao.insertAllPlayers(players)
    }


}