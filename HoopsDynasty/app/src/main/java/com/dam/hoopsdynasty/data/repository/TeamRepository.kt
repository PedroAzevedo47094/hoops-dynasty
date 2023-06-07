package com.dam.hoopsdynasty.data.repository

import com.dam.hoopsdynasty.data.dao.TeamDao
import com.dam.hoopsdynasty.data.model.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {

    val teams: Flow<List<Team>> = teamDao.getAllTeams()

    fun getTeam(abbreviation: String): Flow<Team> {
        return teamDao.getTeam(abbreviation)
    }

    fun getTeamsByConference(conference: String): Flow<List<Team>> {
        return teamDao.getTeamsByConference(conference)
    }

    fun getTeamsByDivision(division: String): Flow<List<Team>> {
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

    fun getAllTeams(): Flow<List<Team>> {
        return teamDao.getAllTeams()
    }
}
