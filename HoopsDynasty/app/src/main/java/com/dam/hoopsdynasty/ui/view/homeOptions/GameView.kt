package com.dam.hoopsdynasty.ui.view.homeOptions

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.simulation.GameSimulation
import com.dam.hoopsdynasty.ui.view.BuzzerView
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerImageWithValue
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.viewmodel.GamesSimulationViewModel
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun GameView(mainViewModel: MainViewModel, navController: NavController) {
    val context = LocalContext.current
    val managerViewModel = mainViewModel.managerViewModel
    val gameViewModel = mainViewModel.gameViewModel
    val teamViewModel = mainViewModel.teamViewModel
    val gsViewModel = mainViewModel.gamesSimulationViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val managerTeam: Team? = teamLiveData.value

    if(theManager != null) {
        gsViewModel.setManager(theManager!!)
    }
    //get the next game to play
    val nextGames = theManager?.team?.games
    var nextGame = nextGames?.get(0)


    val gameIsOver by gsViewModel.gameIsOver.collectAsState()
    val winnerId by gsViewModel.winner.collectAsState()
    val winnerTeam by gsViewModel.winnerTeam.collectAsState()

    val homeScore by gsViewModel.homeScore.collectAsState()
    val awayScore by gsViewModel.awayScore.collectAsState()

//    nextGames?.forEach { game ->
//        if (game.winner == null) {
//            nextGame = game
//            return@forEach
//        }
//    }

    var gameSimulation: GameSimulation? = null
    nextGame?.let { game ->
        gameSimulation = GameSimulation(mainViewModel, game, gsViewModel)
        //gsViewModel.setGameSimulation(mainViewModel, game, gameSimulation)


        val homeTeamLiveData = teamViewModel.getTeam(game.homeTeamId)
        val homeTeam = homeTeamLiveData.observeAsState().value

        val awayTeamLiveData = teamViewModel.getTeam(game.awayTeamId)
        val awayTeam = awayTeamLiveData.observeAsState().value

        if (homeTeam != null && awayTeam != null) {
            gsViewModel.setHomeTeam(homeTeam)
            gsViewModel.setAwayTeam(awayTeam)
        }

    }

    val homeTeam = gsViewModel.homeTeam.value
    val awayTeam = gsViewModel.awayTeam.value

    if (gameSimulation != null) {
        gameSimulation?.navigateToOtherComposable = {
            // Navigate to another composable here
            // Example: use navigation library or set a flag to switch to another screen
        }
    }

    Column() {

        if (gameIsOver && winnerTeam != null) {
            BuzzerView(
                homeTeam!!, awayTeam!!, homeScore, awayScore, winnerTeam!!,
                nextGame!!, teamViewModel, managerViewModel,
                navController,
            )
        } else {
            if (homeTeam != null && awayTeam != null) {
                GameRunning(homeTeam, awayTeam, homeScore, awayScore, mainViewModel, gameSimulation, gsViewModel, context)
            }
        }

    }
}

@Composable
fun GameRunning(
    homeTeam: Team,
    awayTeam: Team,
    homeScore: Int,
    awayScore: Int,
    mainViewModel: MainViewModel,
    gameSimulation: GameSimulation?,
    gsViewModel: GamesSimulationViewModel,
    context: Context

) {
    Row {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .align(Alignment.CenterVertically)
        )
        {

            GameScore(
                homeTeam, awayTeam, homeScore = homeScore, awayScore = awayScore, mainViewModel,
                gameSimulation!!, gsViewModel, context
            )

        }
    }
    Row(modifier = Modifier.fillMaxSize()) {

        val homeTeamLineUp = homeTeam.positions
        val awayTeamLineUp = awayTeam.positions
        LineUp(homeTeamLineUp, awayTeamLineUp, context, gsViewModel)


    }
}


@Composable
fun GameScore(
    homeTeam: Team,
    awayTeam: Team,
    homeScore: Int,
    awayScore: Int,
    mainViewModel: MainViewModel,
    gameSimulation: GameSimulation,
    gsViewModel: GamesSimulationViewModel,
    context: Context
) {

    gameSimulation.setHomeTeam(homeTeam)
    gameSimulation.setAwayTeam(awayTeam)


    LaunchedEffect(Unit) {
        delay(2000) // Wait for 2 seconds
        gameSimulation.startGame()
    }

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

                val fontSize = if(awayScore >= 100 && homeScore >= 100) 36 else 40

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


@Composable
fun LineUp(
    homeTeamLineUp: Map<String, Player?>,
    awayTeamLineUp: Map<String, Player?>,
    context: Context,
    gsViewModel: GamesSimulationViewModel
) {

    val homeTeamPg = homeTeamLineUp["PG"]
    val awayTeamPg = awayTeamLineUp["PG"]
    val time by gsViewModel.time.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.Transparent)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.court),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)) // Clip the image with rounded corners
        )
        Column(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            StartersAway(awayTeamLineUp = awayTeamLineUp, context = context)
            MiddleCourtLine(awayTeamPg, homeTeamPg, time, context)
            StartersHome(homeTeamLineUp = homeTeamLineUp, context = context)

        }
    }
}

@Composable
fun MiddleCourtLine(awayTeamPg: Player?, homeTeamPg: Player?, time: String, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(.80f)
                    .padding(top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Away",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Home",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box() {
                    PlayerImageWithValue(player = awayTeamPg, context = context)
                }

                Box() {
                    PlayerImageWithValue(player = homeTeamPg, context = context)
                }
            }

        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 10.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(.80f),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Color.White.copy(alpha = 0.19f),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(4.dp))
                ) {


                    Text(
                        text = time,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }

}

@Composable
fun StartersAway(awayTeamLineUp: Map<String, Player?>, context: Context) {

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        val C = awayTeamLineUp["C"]
        val PF = awayTeamLineUp["PF"]

        Box() {
            PlayerImageWithValue(player = C, context = context)
        }

        Box() {
            PlayerImageWithValue(player = PF, context = context)
        }
    }


    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Position players evenly horizontally
    ) {
        val SF = awayTeamLineUp["SF"]
        val SG = awayTeamLineUp["SG"]


        Box() {
            PlayerImageWithValue(player = SF, context = context)
        }

        Box() {
            PlayerImageWithValue(player = SG, context = context)
        }

    }

}

@Composable
fun StartersHome(homeTeamLineUp: Map<String, Player?>, context: Context) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Position players evenly horizontally
    ) {
        val SF = homeTeamLineUp["SF"]
        val SG = homeTeamLineUp["SG"]


        Box() {
            PlayerImageWithValue(player = SF, context = context)
        }

        Box() {
            PlayerImageWithValue(player = SG, context = context)
        }

    }
    Spacer(modifier = Modifier.height(4.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        val C = homeTeamLineUp["C"]
        val PF = homeTeamLineUp["PF"]

        Box() {
            PlayerImageWithValue(player = C, context = context)
        }

        Box() {
            PlayerImageWithValue(player = PF, context = context)
        }
    }
}






