package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.simulation.GameSimulation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GamesSimulationViewModel(application: Application) : AndroidViewModel(application) {

    val homeTeam = mutableStateOf<Team?>(null)
    val awayTeam = mutableStateOf<Team?>(null)

    private val _homeScore = MutableStateFlow<Int>(0)
    val homeScore = _homeScore.asStateFlow()

    private val _awayScore = MutableStateFlow<Int>(0)
    val awayScore = _awayScore.asStateFlow()

    private val _time = MutableStateFlow<String>("48:00")
    val time = _time.asStateFlow()

//    private var theGameSimulation: GameSimulation? = null
//
//    fun setGameSimulation(gameSimulation: GameSimulation) {
//        theGameSimulation = gameSimulation
//    }

    fun setTime(time: String) {
        _time.value = time
    }

    fun setHomeTeam(homeTeam: Team) {
        this.homeTeam.value = homeTeam
        //theGameSimulation?.setHomeTeam(homeTeam)
    }

    fun setAwayTeam(awayTeam: Team) {
        this.awayTeam.value = awayTeam
       // theGameSimulation?.setAwayTeam(awayTeam)
    }

    fun updateScores(theHomeScore: Int, theAwayScore: Int) {
        _homeScore.value = theHomeScore
        _awayScore.value = theAwayScore

        Log.d("GameSimulation", "updateScores: homeScore = $theHomeScore, awayScore = $theAwayScore")
        Log.d("GameSimulation", "updateScores: homeScore = ${homeScore.value}, awayScore = ${awayScore.value}")

    }

    fun startGame() {

       // theGameSimulation?.startGame()
    }


}