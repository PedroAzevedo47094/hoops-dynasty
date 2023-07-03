package com.dam.hoopsdynasty.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val managerViewModel: ManagerViewModel
    val playerViewModel: PlayerViewModel
    val gameViewModel: GameViewModel
    val teamViewModel: TeamViewModel
    val seasonViewModel: SeasonViewModel
    val gamesSimulationViewModel: GamesSimulationViewModel

    init {
        managerViewModel = ManagerViewModel(application)
        playerViewModel = PlayerViewModel(application)
        gameViewModel = GameViewModel(application)
        teamViewModel = TeamViewModel(application)
        seasonViewModel = SeasonViewModel(application)
        gamesSimulationViewModel = GamesSimulationViewModel(application)
    }




}

