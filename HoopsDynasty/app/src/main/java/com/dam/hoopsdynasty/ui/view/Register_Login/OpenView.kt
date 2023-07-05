package com.dam.hoopsdynasty.ui.view.Register_Login

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalTextApi::class)
@Composable
fun ManagerInfo(viewModel: MainViewModel, login: Boolean, navController: NavController, millis: Long) {

    HoopsDynastyTheme() {
        MaterialTheme {
            var isVisible by remember { mutableStateOf(false) }
            val offsetY by animateDpAsState(
                targetValue = if (isVisible) (-300f).dp else 0.dp,
                animationSpec = tween(durationMillis = millis.toInt())
            )

            LaunchedEffect(Unit) {
                isVisible = true
            }

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
//

                    val gradientColors = listOf(Color(0xff64EFFC), Color(0xffD39A63))

                    Text(
                        text = "Hoops Dynasty",
                        style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = gradientColors
                            )
                        ),
                        modifier = Modifier
                           .offset(y = offsetY),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraLight
                    )

                }

            }
            var showContent by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                delay(millis)
                showContent = true
            }

            if (showContent) {
                // Your content here
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (login) {
                        ManagerLogin(viewModel, navController)
                    } else {
                        ManagerRegister(viewModel, navController)
                    }
                }
            }


        }
    }
}