package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dam.hoopsdynasty.data.database.HoopsDynastyDatabase
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.data.repository.TeamRepository
import com.dam.hoopsdynasty.ui.view.homeOptions.PlayerItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TeamRepository =
        TeamRepository(HoopsDynastyDatabase.getDatabase(application).teamDao())

    private val teams: LiveData<List<Team>> = repository.getAllTeams()

    private var standings: List<Team> = mutableListOf()

    //val teams: LiveData<List<Team>> = repository.teams

    fun getTeam(abbreviation: String?): LiveData<Team> {
        return repository.getTeam(abbreviation)
    }

    var isCurrentlyDraggin by mutableStateOf(false)
        private set

    var items by mutableStateOf(emptyList<PlayerItem>())

    val managerViewModel: ManagerViewModel = ManagerViewModel(application)


    fun startDraggin() {
        isCurrentlyDraggin = true
        isSwamped = false
    }

    fun stopDraggin() {
        isCurrentlyDraggin = false
    }


     fun calculateStarters(team: Team?): Map<String, Player?>? {
        return team?.positions
    }

     fun calculateBench(team: Team?): List<Player>? {
        return team?.bench
    }

    var isSwamped = false

    fun onPlayerSwap(dstarterPlayer: PlayerItem, dbenchPlayer: PlayerItem, manager: Manager) {
        //put the starter player on the bench and the bench player in the starting lineup in the player position
        if (isSwamped) {
            return // Function already executed, do nothing
        }

        dbenchPlayer.player?.let { Log.d("PlayerSwap", it.firstName) }

        val team = manager.team!!



        val starterPlayer = dstarterPlayer.player!!
        val benchPlayer = dbenchPlayer.player!!

        var starters: MutableMap<String, Player?> = team.positions.toMutableMap()
        var theBench: MutableList<Player> = team.bench?.toMutableList() ?: mutableListOf()

        val positionToUpdate = starters.entries.find { it.value == starterPlayer }?.key

        //set the position positionToUpdate to becnhPlayer
        if (positionToUpdate != null) {
            starters[positionToUpdate] = null
        }

        theBench.remove(benchPlayer)

        if (positionToUpdate != null) {
            starters[positionToUpdate] = benchPlayer
        }
        theBench.add(starterPlayer)

        team.positions = starters
        team.bench = theBench
        manager.team = team


//        // Update the database using Room

        updateTeam(team)
        managerViewModel.updateManager(manager)
        theBench.forEachIndexed { index, player ->
            Log.d("PlayerSwap", "Bench Player ${index + 1}: $player")
        }
        isSwamped = true

    }


    fun updateTeam(team: Team) = viewModelScope.launch {
        repository.updateTeam(team)
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

    fun insertAllTeams(teams: List<Team>) = viewModelScope.launch {
        repository.insertAllTeams(teams)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}
