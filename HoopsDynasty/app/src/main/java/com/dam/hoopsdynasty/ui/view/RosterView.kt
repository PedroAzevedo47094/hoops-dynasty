package com.dam.hoopsdynasty.ui.view


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScopeInstance.align
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
import androidx.compose.material3.Text
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
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerImageWithValue
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

    val starters = team?.positions

    val bench = team?.bench


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
                    .padding(8.dp)
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    text = "Roster",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.White,
                    fontSize = 16.sp
                )
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
            Column(modifier = Modifier.padding(8.dp)){
                // Add your content within the Row
                if (starters != null) {
                    Starters(starters = starters, context = context, onPlayerSwap = onPlayerSwap)
                }
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
            Bench(bench = bench, context = context)
        }




    }


    // Define a list to hold draggable players
    val draggablePlayers = mainViewModel.draggablePlayers

    // Function to handle player swap
    val onPlayerSwap: (DraggablePlayer, DraggablePlayer) -> Unit = { draggedPlayer, targetPlayer ->
        mainViewModel.onPlayerSwap(draggedPlayer, targetPlayer)
    }

    // Display the starters
    Starters(starters = starters, context = context, onPlayerSwap = onPlayerSwap)

    // Display the bench players
    BenchPlayers(bench = bench, context = context, onPlayerSwap = onPlayerSwap)

}

@Composable
fun Starters(starters: Map<String, Player?>?, context: Context, onPlayerSwap: (DraggablePlayer, DraggablePlayer) -> Unit) {

    //space
    Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            val C = starters?.get("C")
            val PF = starters?.get("PF")

            Box(

            ) {
                //PlayerImageWithValue(player = C, context = context)
                DraggablePlayerImageWithValue(player = C, context = context, onPlayerSwap = onPlayerSwap)
            }

            Box(

            ) {
                //PlayerImageWithValue(player = PF, context = context)
                DraggablePlayerImageWithValue(player = PF, context = context, onPlayerSwap = onPlayerSwap)
            }
        }
    Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween // Position players evenly horizontally
        ) {
            val SF = starters?.get("SF")
            val SG = starters?.get("SG")

            Box(

            ) {
                PlayerImageWithValue(player = SF, context = context)
            }

            Box(

            ) {
                PlayerImageWithValue(player = SG, context = context)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center // Center the player horizontally
        ) {
            val PG = starters?.get("PG")

            Box(

            ) {
                PlayerImageWithValue(player = PG, context = context)
            }
        }


}


@Composable
fun Bench(bench: List<Player>?, context: Context) {

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
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                    .padding(horizontal = 4.dp) // Add horizontal padding for space between players
                            ) {
                                PlayerImageWithValue(player = player, context = context)
                            }
                        }
                    }
                }
            }
        }
}