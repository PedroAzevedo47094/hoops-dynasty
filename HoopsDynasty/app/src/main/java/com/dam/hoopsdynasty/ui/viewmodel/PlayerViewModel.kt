package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlayerRepository =
        PlayerRepository(HoopsDynastyDatabase.getDatabase(application).playerDao())

    val players: Flow<List<Player>> = repository.players

    fun getPlayer(id: Int): Flow<Player> {
        return repository.getPlayer(id)
    }

    fun getPlayersByPosition(position: String): Flow<List<Player>> {
        return repository.getPlayersByPosition(position)
    }

    fun getPlayersByRating(rating: Float): Flow<List<Player>> {
        return repository.getPlayersByRating(rating)
    }

    fun getPlayersByRatingAndPosition(rating: Float, position: String): Flow<List<Player>> {
        return repository.getPlayersByRatingAndPosition(rating, position)
    }

    fun getPlayersByRatingRange(rating: Float, rating2: Float): Flow<List<Player>> {
        return repository.getPlayersByRatingRange(rating, rating2)
    }

    fun getPlayersByRatingRangeAndPosition(
        rating: Float,
        rating2: Float,
        position: String
    ): Flow<List<Player>> {
        return repository.getPlayersByRatingRangeAndPosition(rating, rating2, position)
    }

    fun getPlayersByTeam(teamAbbreviation: String): Flow<List<Player>> {
        return repository.getPlayersByTeam(teamAbbreviation)
    }

    fun insertPlayer(player: Player) = viewModelScope.launch {
        repository.insertPlayer(player)
    }

    fun updatePlayer(player: Player) = viewModelScope.launch {
        repository.updatePlayer(player)
    }

    fun insertAllPlayers(players: List<Player>) = viewModelScope.launch {
        repository.insertAllPlayers(players)
    }


}