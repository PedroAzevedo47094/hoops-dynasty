package com.dam.hoopsdynasty.data.repository

import androidx.lifecycle.LiveData
import com.dam.hoopsdynasty.data.dao.TeamDao
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {

    val teams: LiveData<List<Team>> = teamDao.getAllTeams()

    fun getTeam(abbreviation: String?): LiveData<Team> {
        return teamDao.getTeam(abbreviation)
    }

    fun getTeamsByConference(conference: String): LiveData<List<Team>> {
        return teamDao.getTeamsByConference(conference)
    }

    fun getTeamsByDivision(division: String): LiveData<List<Team>> {
        return teamDao.getTeamsByDivision(division)
    }

    suspend fun insertTeam(team: Team) {
        teamDao.insertTeam(team)
    }

    suspend fun updateTeam(team: Team) {
        teamDao.updateTeam(team)
    }

    suspend fun insertAllTeams(teams: List<Team>) {
        teamDao.insertAllTeams(teams)
    }

    fun getAllTeams(): LiveData<List<Team>> {
        return teamDao.getAllTeams()
    }
}
