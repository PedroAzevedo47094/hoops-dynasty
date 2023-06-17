package com.dam.hoopsdynasty.ui.view.homeOptions


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.DragTarget
import com.dam.hoopsdynasty.ui.DraggableScreen
import com.dam.hoopsdynasty.ui.DropItem
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerImageWithValue
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
import com.dam.hoopsdynasty.ui.viewmodel.TeamViewModel
import kotlin.math.roundToInt


data class PlayerItem(
    val player: Player?,
    var position: String?
)


@Composable
fun RosterView(mainViewModel: MainViewModel, navController: NavController) {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val managerViewModel = mainViewModel.managerViewModel
    val teamViewModel = mainViewModel.teamViewModel

    val theManager by managerViewModel.getManager().observeAsState()
    val teamAbr = theManager?.team?.abbreviation

    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value

    val starters = team?.positions

    val bench = team?.bench

    DraggableScreen {

        Column {
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
                        text = "Roster",
                        //modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
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
            val screenHeight = LocalConfiguration.current.screenHeightDp
            val desiredHeight = (screenHeight * 0.50).dp

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .heightIn(min = desiredHeight, max = desiredHeight)
                    .background(Color.Transparent) // Set background color to transparent
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.half_court),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)) // Clip the image with rounded corners
                )
                Column(modifier = Modifier.padding(8.dp)) {
                    // Add your content within the Row
                    if (starters != null) {
                        Starters(
                            starters = starters,
                            context = context,
                            teamViewModel = teamViewModel,
                            team = team!!
                        )
                    }

                    var teamRating = 0f
                    starters?.forEach { (position, player) ->
                        if (player != null) {
                            teamRating += player.rating
                        }
                    }
                    teamRating /= 5
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                            .align(Alignment.CenterHorizontally)


                    ) {

                        Text(
                            text = "Team Rating: ${teamRating.roundToInt()}",
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }


//                    Box(){
//                        Text(text = )
//                    }
                }
            }


            val desiredHeight2 = (screenHeight * 0.30).dp

            //add space
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .background(
                        Color(0xFFD9D9D9).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth(0.9f)
                    .heightIn(min = desiredHeight2, desiredHeight2)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)

            ) {
                if (bench != null) {
                    Bench(
                        bench = bench,
                        context = context,
                        teamViewModel = teamViewModel,
                        team = team!!
                    )
                }
            }

        }
    }
}

@Composable
fun Starters(
    starters: Map<String, Player?>?,
    context: Context,
    teamViewModel: TeamViewModel,
    team: Team
) {

    //space
    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        val C = starters?.get("C")
        val PF = starters?.get("PF")

        val pl1 = PlayerItem(player = C, position = C?.position1)
        teamViewModel.items = teamViewModel.items.plus(pl1)
        DropItem<PlayerItem>(modifier = Modifier) { isInBound, playerItem ->
            if (playerItem != null) {
                teamViewModel.onPlayerSwap(
                    dstarterPlayer = pl1,
                    dbenchPlayer = playerItem,
                    team = team
                )
            }
//            DragTarget(
//                dataToDrop = pl1,
//                viewModel = teamViewModel
//            ) {
            Box() {
                PlayerImageWithValue(player = C, context = context)
            }
//            }
        }

        val pl2 = PlayerItem(player = PF, position = PF?.position1)
        teamViewModel.items = teamViewModel.items.plus(pl2)
        DropItem<PlayerItem>(modifier = Modifier) { isInBound, playerItem ->
            if (playerItem != null) {
                teamViewModel.onPlayerSwap(
                    dstarterPlayer = pl2,
                    dbenchPlayer = playerItem,
                    team = team
                )
            }
//            DragTarget(
//                dataToDrop = pl2,
//                viewModel = teamViewModel
//            ) {
            Box() {
                PlayerImageWithValue(player = PF, context = context)
            }
//            }
        }

    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween // Position players evenly horizontally
    ) {
        val SF = starters?.get("SF")
        val SG = starters?.get("SG")

        val pl3 = PlayerItem(player = SF, position = SF?.position1)
        teamViewModel.items = teamViewModel.items.plus(pl3)
        DropItem<PlayerItem>(modifier = Modifier) { isInBound, playerItem ->
            if (playerItem != null) {
                teamViewModel.onPlayerSwap(
                    dstarterPlayer = pl3,
                    dbenchPlayer = playerItem,
                    team = team
                )
            }
//            DragTarget(
//                dataToDrop = pl3,
//                viewModel = teamViewModel
//            ) {
            Box() {
                PlayerImageWithValue(player = SF, context = context)
            }
//            }
        }

        val pl4 = PlayerItem(player = SG, position = SG?.position1)
        teamViewModel.items = teamViewModel.items.plus(pl4)
        DropItem<PlayerItem>(modifier = Modifier) { isInBound, playerItem ->
            if (playerItem != null) {
                teamViewModel.onPlayerSwap(
                    dstarterPlayer = pl4,
                    dbenchPlayer = playerItem,
                    team = team
                )
            }
//            DragTarget(
//                dataToDrop = pl4,
//                viewModel = teamViewModel
//            ) {
            Box() {
                PlayerImageWithValue(player = SG, context = context)
            }
//            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Center the player horizontally
    ) {
        val PG = starters?.get("PG")

        val pl5 = PlayerItem(player = PG, position = PG?.position1)
        teamViewModel.items = teamViewModel.items.plus(pl5)

        DropItem<PlayerItem>(modifier = Modifier) { isInBound, playerItem ->
            if (playerItem != null) {
                teamViewModel.onPlayerSwap(
                    dstarterPlayer = pl5,
                    dbenchPlayer = playerItem,
                    team = team
                )
            }
//            DragTarget(
//                dataToDrop = pl5,
//                viewModel = teamViewModel
//            ) {
            Box() {
                PlayerImageWithValue(player = PG, context = context)
            }
//            }
        }
    }


}


@Composable
fun Bench(bench: List<Player>?, context: Context, teamViewModel: TeamViewModel, team: Team) {


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val playersPerRow = 4
        val rows = bench?.chunked(playersPerRow)

        rows?.forEach { row ->

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEach { player ->
                        val pl = PlayerItem(player = player, position = player.position1)
                        //teamViewModel.items = teamViewModel.items.plus(pl)
                        DropItem<PlayerItem>(modifier = Modifier.fillMaxHeight()) { isInBound, playerItem ->
                            if (playerItem != null) {
                                teamViewModel.onPlayerSwap(
                                    dstarterPlayer = playerItem,
                                    dbenchPlayer = pl,
                                    team = team
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(horizontal = 4.dp)

                            ) {
                                DragTarget(
                                    dataToDrop = pl,
                                    viewModel = teamViewModel
                                ) {
                                    PlayerImageWithValue(player = player, context = context)
                                }
                            }

                        }

                    }
                }
            }
        }
    }

}

