package com.dam.hoopsdynasty.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.dam.hoopsdynasty.ui.theme.HoopsDynastyTheme

@Composable
fun GradientBackground() {
    HoopsDynastyTheme {
        MaterialTheme() {
//    val gradientColors = listOf(
//        Color(0xff252338),
//        Color(0xff252338).copy(alpha = 0.89f),
//        Color(0xff252338).copy(alpha = 0.83f)
//    )

            Box(
                modifier = Modifier.fillMaxSize().background(colorScheme.primary)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF262433).copy(alpha = 0.85f),
                                    Color(0xff252338)
                                ),


                                )
                        )
                )

                // Your content here
            }
            /*Box(
        modifier = Modifier
            .fillMaxSize().background(Color(0xffA7A9AD))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(gradientColors[0], gradientColors[1]),
                        startY = 0f,
                        endY = 0.6f
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(gradientColors[1], gradientColors[2]),
                        startY = 0.6f,
                        endY = 1f
                    )
                )
        )

    }*/
        }

    }
}
