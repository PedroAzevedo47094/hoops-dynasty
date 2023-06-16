package com.dam.hoopsdynasty.ui.view

import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
@Composable
fun RosterView(mainViewModel: MainViewModel, navController: NavController) {
    val context = LocalContext.current
    val managerViewModel = mainViewModel.managerViewModel
    val teamViewModel = mainViewModel.teamViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value

    Column {
        Row(Modifier.fillMaxWidth()) {
            // Show the team name if available

            if(team != null) {
                TeamLogo(team = team, context = context)
            }

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Players",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

        }
    }
}


