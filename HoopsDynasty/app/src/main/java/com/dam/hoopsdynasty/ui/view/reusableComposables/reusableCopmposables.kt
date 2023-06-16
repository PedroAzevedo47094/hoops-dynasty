package com.dam.hoopsdynasty.ui.view.reusableComposables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.hoopsdynasty.data.model.Player
import com.dam.hoopsdynasty.data.model.Team
import kotlin.math.roundToInt

@Composable
fun TeamLogo(team: Team?, context: Context) {

    val logo = "@drawable/${team?.logo}"

    val logoResourceId = context.resources.getIdentifier(logo, "drawable", context.packageName)

    Image(
        painter = painterResource(logoResourceId),
        contentDescription = team?.abbreviation,
        modifier = Modifier.fillMaxWidth()
    )

}

@Composable
fun PlayerImage(player: Player?, context: Context) {

    //take the .png out of the player image name


    val image = player?.image?.removeSuffix(".png")


    val playerImage = "@drawable/${image}"

    val playerImageResourceId =
        context.resources.getIdentifier(playerImage, "drawable", context.packageName)

    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .border(1.dp, Color.Black, CircleShape)
            .aspectRatio(1f) // Maintain aspect ratio of the image
    ) {
        Image(
            painter = painterResource(playerImageResourceId),
            contentDescription = player?.image,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }


}

@Composable
fun PlayerImageWithValue(player: Player?, context: Context) {
    val playerPosition = player?.position1

    //get the first letter of the position
    val playerPositionFirstLetter = playerPosition?.get(0)


    val playerValue = player?.rating

    //round the value to the nearest whole number
    val playerValueRounded = playerValue?.roundToInt()


    val playerLabel = "$playerPositionFirstLetter $playerValueRounded"

    Column {
        PlayerImage(player, context)
        Text(
            text = playerLabel,
            fontSize = 15.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}

