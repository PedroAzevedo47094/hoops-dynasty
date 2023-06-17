package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.TeamRepository
import com.dam.hoopsdynasty.ui.view.PlayerItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository =
        TeamRepository(HoopsDynastyDatabase.getDatabase(application).teamDao())

    val teams: LiveData<List<Team>> = repository.teams

    fun getTeam(abbreviation: String?): LiveData<Team> {
        return repository.getTeam(abbreviation)
    }

    var isCurrentlyDraggin by mutableStateOf(false)
        private set

    var items by mutableStateOf(emptyList<PlayerItem>())


    fun startDraggin() {
        isCurrentlyDraggin = true
    }

    fun stopDraggin() {
        isCurrentlyDraggin = false
    }


    fun onPlayerSwap(dstarterPlayer: PlayerItem, dbenchPlayer: PlayerItem, team: Team) {
        //put the starter player on the bench and the bench player in the starting lineup in the player position

        val starterPlayer = dstarterPlayer.player!!
        val benchPlayer = dbenchPlayer.player!!

        var starters: MutableMap<String, Player?> = team.positions.toMutableMap()
        var theBench: MutableList<Player> = team.bench?.toMutableList() ?: mutableListOf()

        val positionToUpdate = starters.entries.find { it.value == starterPlayer }?.key

        //set the position positionToUpdate to becnhPlayer
        if (positionToUpdate != null) {
            starters[positionToUpdate] = benchPlayer
        }

        theBench.add(starterPlayer)
        theBench.remove(benchPlayer)

        team.positions = starters
        team.bench = theBench

//        // Update the database using Room
        updateTeam(team)
    }

    fun getTeamsByConference(conference: String): LiveData<List<Team>> {
        return repository.getTeamsByConference(conference)
    }

    fun getTeamsByDivision(division: String): LiveData<List<Team>> {
        return repository.getTeamsByDivision(division)
    }

    fun getAllTeams(): LiveData<List<Team>> {
        return repository.getAllTeams()
    }

    fun insertTeam(team: Team) = viewModelScope.launch {
        repository.insertTeam(team)
    }

    fun updateTeam(team: Team) = viewModelScope.launch {
        repository.updateTeam(team)
    }

    fun insertAllTeams(teams: List<Team>) = viewModelScope.launch {
        repository.insertAllTeams(teams)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
