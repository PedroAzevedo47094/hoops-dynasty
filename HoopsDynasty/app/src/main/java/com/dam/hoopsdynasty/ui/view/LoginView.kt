package com.dam.hoopsdynasty.ui.view


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme
import kotlinx.coroutines.delay

//last
@Composable
fun Login() {

    HoopsDynastyTheme() {
        MaterialTheme {
            var isVisible by remember { mutableStateOf(false) }
            val offsetY by animateDpAsState(
                targetValue = if (isVisible) (-300f).dp else 0.dp,
                animationSpec = tween(durationMillis = 2000)
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
                    Text(
                        text = "Hoops Dynasty",
                        color = Color.White,
                        modifier = Modifier
                            .offset(y = offsetY),
                        style = TextStyle(
                            fontSize = 40.sp,
                            shadow = Shadow(
                                color = Color.Gray, offset = Offset(5.0f, 10.0f), blurRadius = 2f
                            )
                        )
                    )

                }

            }
            var showContent by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                delay(2000)
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
                    LoginForm()
                }
            }



            }
        }
    }



@Preview
@Composable
fun PreviewMyApp() {
    Background()
    Login()
}

@Composable
fun LoginForm() {
    OutlinedTextField(
        value = "",
        onValueChange = { /* TODO: Handle email change */ },
        label = { Text(text = "Email", color = Color.White) },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.White),
                shape = MaterialTheme.shapes.medium
            )

    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = "",
        onValueChange = { /* TODO: Handle password change */ },
        label = { Text(text = "Password", color = Color.White) },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.White),
                shape = MaterialTheme.shapes.medium
            )
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedButton(
        onClick = { /* TODO: Handle login button click */ },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.White),
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Text(text = "Login", color = Color.White)
    }
}