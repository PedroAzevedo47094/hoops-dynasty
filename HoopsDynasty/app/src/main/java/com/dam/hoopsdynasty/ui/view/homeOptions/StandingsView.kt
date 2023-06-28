package com.dam.hoopsdynasty.ui.view.homeOptions

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel


@Composable
fun StandingsView(mainViewModel: MainViewModel, navController: NavController) {

    val context = LocalContext.current
    val teamViewModel = mainViewModel.teamViewModel

    val teams by teamViewModel.getAllTeams().observeAsState(initial = emptyList())

    val managerViewModel = mainViewModel.managerViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value

    val teamsStandings = teams.sortedWith(
        compareByDescending<Team> { it.wins }
            .thenBy { it.losses }
            .thenByDescending { it.name }
    )

    Column() {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = "Trohpy"
                )
            }

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    text = "Standings",

                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .align(Alignment.CenterVertically)
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
            ) {
                TextButton(
                    onClick = {

                        navController.navigate("home")

                    },
                    // Adjust the padding values for the TextButton
                    colors = ButtonDefaults.textButtonColors(
                        // backgroundColor = Color.Transparent, // Set the background color to transparent
                        contentColor = Color.White, // Set the text color
                    ),
                    shape = RoundedCornerShape(8.dp), // Apply rounded corner shape
                    contentPadding = PaddingValues(5.dp) // Remove padding from the button
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Home",
                            fontSize = 18.sp
                        )

                    }
                }
            }
        }


        LazyColumn {
            teamsStandings.forEach { team ->
                item {
                    
                    TeamBox(
                        team = team,
                        context = context,
                        place = teamsStandings.indexOf(team) + 1
                    )

                }
            }
        }
    }
}


@Composable
fun TeamBox(team: Team, context: Context, place: Int) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(text = place.toString(), color = Color.White, fontSize = 16.sp)

                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(8.dp)
                ) {
                    TeamLogo(team = team, context = context)
                }

                Column(modifier = Modifier.padding(8.dp)) {
                    Row {

                        Text(
                            text = team.name,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                }
            }
        }
        Box(
            modifier = Modifier.padding(8.dp),

            ) {
            Row() {

                Text(text = team.wins.toString(), color = Color.White)
                Text(text = "-", color = Color.White)
                Text(text = team.losses.toString(), color = Color.White)
            }
        }
    }


}



