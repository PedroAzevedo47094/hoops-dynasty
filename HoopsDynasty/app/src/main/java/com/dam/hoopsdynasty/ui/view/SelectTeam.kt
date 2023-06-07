package com.dam.hoopsdynasty.ui.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.data.model.Manager
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun SelectTeam(viewModel: MainViewModel, navController: NavController) {

    val context = LocalContext.current
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp)),
            ) {
                Text(
                    text = "Choose a Team",
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 10.dp),
                    color = Color.White,
                    fontSize = 23.sp
                )
            }
        }

        val teamViewModel = viewModel.teamViewModel
        val teams by teamViewModel.getAllTeams().collectAsState(initial = emptyList())

        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                .background(Color(0x4DEEEEEE))
        ) {

            LazyRow(Modifier.fillMaxSize()) {
                val chunkedTeams = teams.chunked(5)
                items(chunkedTeams.size) { rowIndex ->
                    Column(Modifier.fillMaxWidth()) {
                        val rowTeams = chunkedTeams[rowIndex]
                        rowTeams.forEach { team ->
                            Column(
                                Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .height(100.dp)
                                    .wrapContentHeight(Alignment.CenterVertically),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(onClick = {
                                  /*  val managerViewModel = viewModel.managerViewModel
                                     managerViewModel.updateManagerWithTeam(team)*/

                                }) {

                                    TeamLogo(team, context)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun TeamLogo(team: Team, context: Context) {

    val logo = "@drawable/${team.logo}"

    val logoResourceId = context.resources.getIdentifier(logo, "drawable", context.packageName)

    Image(
        painter = painterResource(logoResourceId),
        contentDescription = "Andy Rubin",
        modifier = Modifier.fillMaxWidth()
    )

}



