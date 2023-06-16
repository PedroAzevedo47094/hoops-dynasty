package com.dam.hoopsdynasty.ui.view.reusableComposables

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dam.hoopsdynasty.data.model.Team

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

