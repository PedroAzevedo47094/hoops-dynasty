package com.dam.hoopsdynasty.ui.view

import android.content.Context
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.data.model.Game
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.viewmodel.GamesSimulationViewModel
import com.dam.hoopsdynasty.ui.viewmodel.ManagerViewModel
import com.dam.hoopsdynasty.ui.viewmodel.TeamViewModel

@Composable
fun BuzzerView(
    homeTeam: Team, awayTeam: Team, homeScore: Int, awayScore: Int,
    winner: Team,
    game: Game,
    teamViewModel: TeamViewModel,
    managerViewModel: ManagerViewModel,
    gamesSimulationViewModel: GamesSimulationViewModel,
    navController: NavController
) {


    Log.d("BuzzerView", "BuzzerView: winner = $winner")

    val context = LocalContext.current
    val theManager by managerViewModel.getManager().observeAsState()
    val managerTeam = theManager?.team
    if (theManager != null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Game Over!", fontSize = 24.sp, color = colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "The ${winner.name} wins!",
                    fontSize = 24.sp,
                    color = colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))

                FinalGameScore(
                    homeTeam = homeTeam,
                    awayTeam = awayTeam,
                    homeScore = homeScore,
                    awayScore = awayScore,
                    context = context
                )

                Box(
                    modifier = Modifier
                        // Align the button to the bottom center of the screen
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .border(0.7.dp, colorScheme.primary, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center

                ) {
                    TextButton(
                        onClick = {


                            val mutableHGames: MutableList<Int> = homeTeam.games!!.toMutableList()
                            mutableHGames.remove(game.id)

                            homeTeam.games = mutableHGames.toList()


                            val mutableAGames: MutableList<Int> = awayTeam.games!!.toMutableList()
                            mutableAGames.remove(game.id)

                            awayTeam.games = mutableAGames.toList()



                            teamViewModel.updateTeam(homeTeam)
                            teamViewModel.updateTeam(awayTeam)
                            theManager?.let { managerViewModel.updateManager(it.copy(team = if (homeTeam == managerTeam) homeTeam else awayTeam)) }
                            navController.navigate("home")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = colorScheme.primary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        Text(
                            text = "Home",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                        )
                    }
                }
            }

        }
    }
}


@Composable
fun FinalGameScore(
    homeTeam: Team,
    awayTeam: Team,
    homeScore: Int,
    awayScore: Int,
    context: Context
) {

    val fontSize = if (awayScore >= 100 && homeScore >= 100) 36 else 40
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Box(
            modifier = Modifier
                .size(100.dp)
        ) {
            TeamLogo(team = awayTeam, context = context)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(.80f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Away",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column {
                        Text(
                            text = "Home",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = awayScore.toString(),
                            fontSize = fontSize.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column {
                        Text(
                            text = "-",
                            fontSize = fontSize.sp,
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column {
                        Text(
                            text = "$homeScore",
                            fontSize = fontSize.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }


        Box(
            modifier = Modifier
                .size(100.dp)
        ) {
            TeamLogo(team = homeTeam, context = context)
        }
    }
}
