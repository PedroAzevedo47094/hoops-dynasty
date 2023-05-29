package com.dam.hoopsdynasty.ui.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BtnNext(){
    Box(
        modifier = Modifier
            .padding(4.dp) // Adjust the padding values for the Box
            .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))

    ) {
        TextButton(
            onClick = { /* Handle button click here */ },
            modifier = Modifier.padding(
                horizontal = 30.dp,
                vertical = 0.5.dp
            ), // Adjust the padding values for the TextButton
            colors = ButtonDefaults.textButtonColors(
                //backgroundColor = Color.Transparent, // Set the background color to transparent
                contentColor = Color.White, // Set the text color
            ),
            shape = RoundedCornerShape(8.dp), // Apply rounded corner shape
            contentPadding = PaddingValues(0.dp) // Remove padding from the button
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Next",
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Arrow Forward",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
