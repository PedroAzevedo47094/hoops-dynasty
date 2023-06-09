package com.dam.hoopsdynasty.data.repository

import com.dam.hoopsdynasty.data.dao.GameDao
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import androidx.lifecycle.LiveData


class GameRepository(private val gameDao: GameDao) {

    val games: LiveData<List<Game>> = gameDao.getAllGames()

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames()
    }

    fun getGame(id: Int): LiveData<Game> {
        return gameDao.getGame(id)
    }

    fun getGamesBySeason(season: Int): LiveData<List<Game>> {
        return gameDao.getGamesBySeason(season)
    }

    fun getGamesByTeam(team: String): LiveData<List<Game>> {
        return gameDao.getGamesByTeam(team)
    }

    fun getHomeGamesByTeam(team: String): LiveData<List<Game>> {
        return gameDao.getHomeGamesByTeam(team)
    }

    fun getAwayGamesByTeam(team: String): LiveData<List<Game>> {
        return gameDao.getAwayGamesByTeam(team)
    }

    fun getGamesByWinner(team: String): LiveData<List<Game>> {
        return gameDao.getGamesByWinner(team)
    }

    fun getGamesByLoser(team: String): LiveData<List<Game>> {
        return gameDao.getGamesByLoser(team)
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun insertAllGames(games: List<Game>) {
        gameDao.insertAllGames(games)
    }

    suspend fun updateGame(game: Game) {
        gameDao.updateGame(game)
    }
}
