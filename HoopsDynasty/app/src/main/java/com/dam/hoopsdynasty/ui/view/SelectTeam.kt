package com.dam.hoopsdynasty.ui.view

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.data.model.Team


@Composable
fun SelectTeam() {
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

        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                .background(Color(0xFFD9D9D9))
        ){






        }


    }
}


//@Composable
//fun TeamLogo(team: Team) {
//
//    Image(
//        painter = painterResource(id = ),
//        contentDescription = "Andy Rubin",
//        modifier = Modifier.fillMaxWidth()
//    )
//}
//
//
//
