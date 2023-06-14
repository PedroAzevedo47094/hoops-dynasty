package com.dam.hoopsdynasty.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun HomeView(viewModel: MainViewModel) {
    val managerViewModel = viewModel.managerViewModel
    val managerState by managerViewModel.getManager().collectAsState(initial = null)
    val season = viewModel.seasonViewModel
    val seasonState by season.getSeason().collectAsState(initial = null)

    val game = seasonState?.schedule

    // Display the manager information
    Column() {
        /*for(i in 0..10) {
            Text(text = "Game ${game?.get(i)?.homeTeam?.name} vs ${game?.get(i)?.awayTeam?.name}}")
        }*/
    }
    // Add more Text components for other manager properties you want to display
}




