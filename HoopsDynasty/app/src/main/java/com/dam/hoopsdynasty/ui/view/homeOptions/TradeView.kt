package com.dam.hoopsdynasty.ui.view.homeOptions

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerImage
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerRating1
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun TradeView(mainViewModel: MainViewModel, navController: NavController) {
    val context = LocalContext.current
    val seasonViewModel = mainViewModel.seasonViewModel
    val managerViewModel = mainViewModel.managerViewModel

    val season by seasonViewModel.getSeason().observeAsState(initial = null)
    val tradeList = season?.tradeList
    val theManager by managerViewModel.getManager().observeAsState()

    val teamAbr = theManager?.team?.abbreviation

    val teamViewModel = mainViewModel.teamViewModel
    val teamLiveData = teamViewModel.getTeam(teamAbr).observeAsState()
    val team: Team? = teamLiveData.value


    Column() {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    text = "50,000$",

                    color = colorScheme.primary,
                    fontSize = 20.sp
                )
            }

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Text(
                    text = "Marketplace",
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


        LazyColumn {
            tradeList?.forEach { player ->
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    PlayerBox(player = player, context = context)

                }
            }
        }
    }

}


@Composable
fun PlayerBox(player: Player, context: Context) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(0.7.dp, Color.White, shape = RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .weight(0.75f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                PlayerImage(player = player, context = context)

                Column(modifier = Modifier.padding(8.dp)) {
                    Row {
                        var font = 16.sp
                        if (player.lastName.length >= 11) {
                            font = 12.sp
                        }


                        Text(
                            text = "${player.lastName}, ${player.firstName}",
                            fontSize = font,
                            color = Color.White
                        )
                    }
                    Row {
                        Text(text = player.position1, fontSize = 12.sp, color = Color.White)
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(8.dp)
                .weight(0.25f),

            ) {
            Column {
                Row {
                    player.rating.roundToInt()
                    PlayerRating1(player.rating.roundToInt(), context)

                }
                Row {
                    val cost = when (player.rating.roundToInt()) {
                        in 0..49 -> Random.nextInt(10000, 19999)
                        in 50..59 -> Random.nextInt(20000, 29999)
                        in 60..69 -> Random.nextInt(30000, 39999)
                        in 70..79 -> Random.nextInt(40000, 49999)
                        in 80..89 -> Random.nextInt(50000, 59999)
                        in 90..99 -> Random.nextInt(60000, 69999)
                        else -> Random.nextInt(70000, 79999)
                    }
                    val formattedCost =
                        NumberFormat.getNumberInstance(Locale.getDefault()).format(cost)

                    Text(text = "$formattedCost$", fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }


}


