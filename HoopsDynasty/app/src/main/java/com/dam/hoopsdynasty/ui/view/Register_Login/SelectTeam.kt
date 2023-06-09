package com.dam.hoopsdynasty.ui.view.Register_Login

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.data.model.Team
import com.dam.hoopsdynasty.ui.view.reusableComposables.TeamLogo
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel


@Composable
fun SelectTeam(
    viewModel: MainViewModel,
    navController: NavController,
    email: String,
    name: String,
    password: String
) {

    val context = LocalContext.current
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border((0.7.dp), colorScheme.primary, shape = RoundedCornerShape(8.dp)),
            ) {
                Text(
                    text = "Choose a Team",
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 10.dp),
                    color = colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }

        val teamViewModel = viewModel.teamViewModel
        val teams: List<Team>? by teamViewModel.getAllTeams().observeAsState()



        Box(
            modifier = Modifier
                .padding(8.dp)
                .border((0.7.dp), colorScheme.primary, shape = RoundedCornerShape(8.dp))
                .background(Color(0x4DEEEEEE), shape = RoundedCornerShape(8.dp))
        ) {
            LazyRow(Modifier.fillMaxSize()) {
                val chunkedTeams = teams?.chunked(5)
                chunkedTeams?.let {
                    items(it.size) { rowIndex ->
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
                                    Button(
                                        onClick = {
                                            val managerViewModel = viewModel.managerViewModel
                                            managerViewModel.registerManager(
                                                email,
                                                name,
                                                password,
                                                team
                                            )
                                            navController.navigate("roster")
                                        },
                                        elevation = null,
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = Color.Transparent
                                        )

                                    ) {

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
}


