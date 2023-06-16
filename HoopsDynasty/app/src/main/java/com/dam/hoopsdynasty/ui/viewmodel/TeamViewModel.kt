package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.TeamRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository =
        TeamRepository(HoopsDynastyDatabase.getDatabase(application).teamDao())

    val teams: LiveData<List<Team>> = repository.teams

    fun getTeam(abbreviation: String?): LiveData<Team> {
        return repository.getTeam(abbreviation)
    }

    fun changePlayers(starterPlayer : Player, benchPlayer : Player, team : Team) {
        //put the starter player on the bench and the bench player in the starting lineup in the player position

        team.bench = team.bench?.plus(starterPlayer)
        team.bench = team.bench?.minus(benchPlayer)


        //get the position of the starter player
        val starterPosition = team.positions.entries.find { it.value == starterPlayer }?.key

        //put the bench player in the starter position, the positions is a Map<String, Player?>
        if (starterPosition != null) {
            team.positions = team.positions.mapValues { entry ->
                when (entry.value) {
                    starterPlayer -> {
                        benchPlayer
                    }
                    else -> {
                        entry.value
                    }
                }
            }
        }

        // Update the database using Room
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
