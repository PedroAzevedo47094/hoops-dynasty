package com.dam.hoopsdynasty.ui.view



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Login() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Add your login form components here
        // For example:
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle value change */ },
            label = { Text("Username", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth(40f)
                //.border(BorderStroke(width = 1.dp, color = Color.White))
                .padding(bottom = 16.dp),
            textStyle = TextStyle(color = Color.White)
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle value change */ },
            label = { Text("Password", color = Color.White) },

            modifier = Modifier
                .fillMaxWidth(40f)
                //.border(BorderStroke(width = 1.dp, color = Color.White))
                .padding(bottom = 16.dp),
            textStyle = TextStyle(color = Color.White)
        )
        OutlinedButton(
            onClick = { /* Handle login button click */ },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(40f)


        ) {
            Text("Login",color = Color.Gray)
        }
    }
}