package com.dam.hoopsdynasty.ui.view.homeOptions

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogoABV
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun CalendarView(mainViewModel: MainViewModel, navController: NavController) {

    val context = LocalContext.current
    val managerViewModel = mainViewModel.managerViewModel
    val teamViewModel = mainViewModel.teamViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value

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
                if (team != null) {
                    TeamLogo(team = team, context = context)
                }
            }



            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    text = "Calendar",
                    color = colorScheme.primary,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .align(Alignment.CenterVertically)
                    .border(0.7.dp, colorScheme.primary, shape = RoundedCornerShape(8.dp))
            ) {
                TextButton(
                    onClick = {

                        navController.navigate("home")

                    },
                    // Adjust the padding values for the TextButton
                    colors = ButtonDefaults.textButtonColors(
                        // backgroundColor = Color.Transparent, // Set the background color to transparent
                        contentColor = MaterialTheme.colorScheme.primary, // Set the text color
                    ),
                    shape = RoundedCornerShape(8.dp), // Apply rounded corner shape
                    contentPadding = PaddingValues(5.dp) // Remove padding from the button
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Home",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                        )

                    }
                }
            }
        }

        val nextGames = team?.games

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp)
        ) {
            items(nextGames?.size ?: 0) { index ->
                val game = nextGames?.get(index)
                val homeTeamId = game?.homeTeamId
                val awayTeamId = game?.awayTeamId
                val managerTeamId = team?.abbreviation

                val opponentTeam = if (homeTeamId != managerTeamId) homeTeamId else awayTeamId
                val isHomeGame = homeTeamId != managerTeamId

                if (opponentTeam != null) {
                    GameBox(opponentTeam, context, isHomeGame)
                }
            }
        }
    }

}

@Composable
fun GameBox(opponetTeam: String, context: Context, at: Boolean) {

    Box(
        modifier = Modifier
            .padding(2.dp)
            .border(0.7.dp, Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier.padding(start = 2.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (at) "@" else "vs",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(80.dp)
            ) {
                TeamLogoABV(abr = opponetTeam, context = context)
            }
        }
    }


}


















