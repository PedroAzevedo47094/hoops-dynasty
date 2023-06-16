package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.GameRepository

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GameRepository =
        GameRepository(HoopsDynastyDatabase.getDatabase(application).gameDao())

    val games: LiveData<List<Game>> = repository.games

    fun getAllGames(): LiveData<List<Game>> {
        return repository.getAllGames()
    }

    fun getGame(id: Int): LiveData<Game> {
        return repository.getGame(id)
    }

    fun getGamesBySeason(season: Int): LiveData<List<Game>> {
        return repository.getGamesBySeason(season)
    }

    fun getGamesByTeam(team: Team): LiveData<List<Game>> {
        return repository.getGamesByTeam(team)
    }

    fun getHomeGamesByTeam(team: String): LiveData<List<Game>> {
        return repository.getHomeGamesByTeam(team)
    }

    fun getAwayGamesByTeam(team: String): LiveData<List<Game>> {
        return repository.getAwayGamesByTeam(team)
    }

    fun getGamesByWinner(team: String): LiveData<List<Game>> {
        return repository.getGamesByWinner(team)
    }

    fun getGamesByLoser(team: String): LiveData<List<Game>> {
        return repository.getGamesByLoser(team)
    }

    suspend fun insertGame(game: Game) {
        repository.insertGame(game)
    }

    suspend fun insertAllGames(games: List<Game>) {
        repository.insertAllGames(games)
    }

    suspend fun updateGame(game: Game) {
        repository.updateGame(game)
    }

}