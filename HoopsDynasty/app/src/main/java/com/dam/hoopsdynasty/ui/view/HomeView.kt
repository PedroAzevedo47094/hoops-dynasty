package com.dam.hoopsdynasty.ui.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerImage
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogoABV
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@OptIn(ExperimentalTextApi::class)
@Composable
fun HomeView(mainViewModel: MainViewModel, navController: NavController) {

    val context = LocalContext.current
    val managerViewModel = mainViewModel.managerViewModel
    val teamViewModel = mainViewModel.teamViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value

    val gameViewModel = mainViewModel.gameViewModel

    Column {

        Row() {


            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(.95f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(95.dp)

                    ) {
                        if (team != null) {
                            TeamLogoABV(abr = team.abbreviation, context = context)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .align(Alignment.CenterVertically)

                    ) {
                        val gradientColors = listOf(Color(0xff64EFFC), Color(0xffD39A63))
                        Text(
                            text = "Hoops Dynasty",
                            style = TextStyle(
                                brush = Brush.linearGradient(
                                    colors = gradientColors
                                )
                            ),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Light
                        )

                    }

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .padding(start = 10.dp)
                            .align(Alignment.CenterVertically)
                            .border((0.7.dp), colorScheme.primary, shape = RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(2.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "W  -  L",
                                modifier = Modifier.padding(4.dp),
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Text(
                                text = " ${team?.wins}  -  ${team?.losses}",
                                modifier = Modifier.padding(4.dp),
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        val space = 20.dp

        Spacer(modifier = Modifier.padding(space))

        Row() {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            ) {
                TextButton(
                    onClick = {
                        navController.navigate("roster")
                    },
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth()
                        .border(0.7.dp, colorScheme.primary, shape = RoundedCornerShape(8.dp))
                ) {
                    if (team != null) {
                        TeamManagment(team, context)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(space))

        Row() {

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Calendar(navController)
                    }

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Standings(navController)
                    }

                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Trade(navController)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(space))

        if (theManager != null) {
            val teamManager = theManager?.team

            if (teamManager != null) {
                val nextGameId = teamManager.games?.get(0)
                val nextGameState = gameViewModel.getGame(nextGameId!!).observeAsState()
                val nextGame: Game? = nextGameState.value

                /* nextGames?.forEach { game ->
                         if (game.winner == null) {
                             nextGame = game
                             return@forEach
                         }
                     }*/

                Row() {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        TextButton(
                            onClick = { navController.navigate("game") },
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                                .border(
                                    0.7.dp,
                                    colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            if (team != null && nextGame != null) {
                                NextGame(
                                    mainViewModel = mainViewModel,
                                    navController = navController,
                                    team = team,
                                    nextGame = nextGame,
                                    context = context
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NextGame(
    mainViewModel: MainViewModel,
    navController: NavController,
    team: Team,
    nextGame: Game,
    context: Context
) {


    val homeTeam = nextGame?.homeTeamId
    val awayTeam = nextGame?.awayTeamId



    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth(.95f)
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)

            ) {
                if (awayTeam != null) {
                    TeamLogoABV(abr = awayTeam, context = context)
                }
            }

            Text(text = "VS", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Light)

            Box(
                modifier = Modifier
                    .size(100.dp)

            ) {
                if (homeTeam != null) {
                    TeamLogoABV(abr = homeTeam, context = context)
                }
            }
        }

    }

}

@Composable
fun TeamManagment(team: Team, context: Context) {

    val starters = team.positions.values.toList()

    Column(

    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlayerImage(player = starters.get(0), context = context)

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            )
            {
                Text(
                    text = "Roster",
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Light
                )
            }


            PlayerImage(player = starters.get(1), context = context)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(.95f)
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var i = 2
            repeat(3) {
                PlayerImage(player = starters.get(i++), context = context)
            }
        }
    }
}


@Composable
fun Calendar(navController: NavController) {
    TextButton(
        onClick = { navController.navigate("calendar") },
        modifier = Modifier
            .padding(2.dp)
            .border(0.7.dp, colorScheme.primary, shape = RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .aspectRatio(1f) // Maintain aspect ratio of the image
        ) {
            Image(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Calendar",
                modifier = Modifier
                    .fillMaxSize()
                    .scale(0.8f)
            )
        }
    }
}

@Composable
fun Standings(navController: NavController) {

    TextButton(
        onClick = { navController.navigate("standings") },
        modifier = Modifier
            .padding(2.dp)
            .border((0.7.dp), colorScheme.primary, shape = RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .aspectRatio(1f) // Maintain aspect ratio of the image
        ) {
            Image(
                painter = painterResource(id = R.drawable.trophy),
                contentDescription = "Trohpy"
            )
        }

    }
}

@Composable
fun Trade(navController: NavController) {
    TextButton(
        onClick = { navController.navigate("trade") },
        modifier = Modifier
            .padding(2.dp)
            .border(0.7.dp, colorScheme.primary, shape = RoundedCornerShape(8.dp))
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .aspectRatio(1f) // Maintain aspect ratio of the image
        ) {
            Image(
                painter = painterResource(id = R.drawable.marketplace),
                contentDescription = "Trade",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
