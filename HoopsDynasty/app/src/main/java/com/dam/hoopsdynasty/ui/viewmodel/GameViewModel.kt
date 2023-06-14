package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.GameRepository
import kotlinx.coroutines.flow.Flow

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GameRepository =
        GameRepository(HoopsDynastyDatabase.getDatabase(application).gameDao())

    val games: Flow<List<Game>> = repository.games

    fun getGame(id: Int): Flow<Game> {
        return repository.getGame(id)
    }

    fun getGamesBySeason(season: Int): Flow<List<Game>> {
        return repository.getGamesBySeason(season)
    }

    fun getGamesByTeam(team: Team): Flow<List<Game>> {
        return repository.getGamesByTeam(team)
    }

    fun getHomeGamesByTeam(team: String): Flow<List<Game>> {
        return repository.getHomeGamesByTeam(team)
    }

    fun getAwayGamesByTeam(team: String): Flow<List<Game>> {
        return repository.getAwayGamesByTeam(team)
    }

    fun getGamesByWinner(team: String): Flow<List<Game>> {
        return repository.getGamesByWinner(team)
    }

    fun getGamesByLoser(team: String): Flow<List<Game>> {
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