package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.TeamRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository =
        TeamRepository(HoopsDynastyDatabase.getDatabase(application).teamDao())

    val teams: Flow<List<Team>> = repository.teams

    fun getTeam(abbreviation: String): Flow<Team> {
        return repository.getTeam(abbreviation)
    }

    fun getTeamsByConference(conference: String): Flow<List<Team>> {
        return repository.getTeamsByConference(conference)
    }

    fun getTeamsByDivision(division: String): Flow<List<Team>> {
        return repository.getTeamsByDivision(division)
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
