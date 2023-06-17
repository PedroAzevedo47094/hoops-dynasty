package com.dam.hoopsdynasty.ui.view.homeOptions

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.ui.view.reusableComposables.PlayerImage
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun TradeView(mainViewModel: MainViewModel, navController: NavController) {
    val context = LocalContext.current
    val seasonViewModel = mainViewModel.seasonViewModel
    val managerViewModel = mainViewModel.managerViewModel

    val season by seasonViewModel.getSeason().observeAsState()
    val tradeList = season?.tradeList
    val theManager by managerViewModel.getManager().observeAsState()

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Marketplace",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),

                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row {
            LazyColumn(

            ) {
                tradeList?.forEach { trade ->
                    item {
                        Row {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(.95f)
                                    .padding(2.dp)
                                    .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
                                    .align(Alignment.CenterVertically)
                            ) {
                                PlayerBox(player = trade, context = context)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerBox(player: Player, context: Context) {
    Row {
        Box {
            PlayerImage(player = player, context = context)
        }
        Box {
            Column {
                Row {
                    Text(text = "${player.lastName}, ${player.firstName}")
                }
                Row {
                    Text(text = player.position1)
                }
            }
        }
    }
}
