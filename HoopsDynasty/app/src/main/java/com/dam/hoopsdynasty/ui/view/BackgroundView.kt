package com.dam.hoopsdynasty.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme

@Composable
fun Background() {
    HoopsDynastyTheme {
        MaterialTheme() { // Adjust the width as neede
             Box(
                 modifier = Modifier.fillMaxSize()
             ) {
                 val color1 = colorScheme.primary
                 val color2 = colorScheme.secondary
                 val strokeColor = Color.Black
                 Canvas(modifier = Modifier.fillMaxSize()) {
                     val width = size.width
                     val height = size.height

                     // Draw the first color
                     drawRect(
                         color = color2,
                         topLeft = Offset(0f, 0f),
                         size = Size(width, height)
                     )

                     // Draw the triangle with the second color
                     val trianglePath = Path().apply {
                         moveTo(0f, 0f)
                         lineTo(width, 0f)
                         lineTo(width, height)
                         close()
                     }
                     drawPath(trianglePath, color = color1)

                     //Draw a stroke in the diagonal of the screen
                     drawLine(
                         color = strokeColor,
                         start = Offset(0f, 0f),
                         end = Offset(width, height),
                         strokeWidth = 2.dp.toPx()
                     )

                 }
             }
        }
    }

}

