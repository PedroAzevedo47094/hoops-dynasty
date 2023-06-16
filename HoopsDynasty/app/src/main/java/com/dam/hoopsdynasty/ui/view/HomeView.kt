package com.dam.hoopsdynasty.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun HomeView(mainViewModel: MainViewModel) {

    val context = LocalContext.current
    val managerViewModel = mainViewModel.managerViewModel
    val teamViewModel = mainViewModel.teamViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value


    Column {
        //Show manager name
        //Show team name if available

        Text(text = "Manager: ${theManager?.name}")
        Text(text = "Team: ${team?.name}")
    }


}




