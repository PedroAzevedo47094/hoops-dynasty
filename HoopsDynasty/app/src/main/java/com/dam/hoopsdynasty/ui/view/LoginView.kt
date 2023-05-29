package com.dam.hoopsdynasty.ui.view


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
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

@Composable
fun LoginForm() {
    var firstName by remember { mutableStateOf("") }




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
                    text = "Manager Info",
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 10.dp),
                    color = Color.White,
                    fontSize = 23.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Column() {
            Text(
                text = "First Name",
                color = Color.White,
                fontSize = 20.sp,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.19f), shape = RoundedCornerShape(4.dp))
                    .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp)
                    .height(55.dp) // Adjust the height as needed
            ) {
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, // Change the background color here
                        focusedIndicatorColor = Color.Transparent, // Remove the focused underline
                        unfocusedIndicatorColor = Color.Transparent // Remove the unfocused underline
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        var mExpanded by remember { mutableStateOf(false) }

        // Create a list of cities
        val mGenders = listOf("Male", "Female")

        // Create a string value to store the selected city
        var mSelectedText by remember { mutableStateOf("") }

        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Text(
            text = "Gender",
            color = Color.White,
            fontSize = 20.sp,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(alpha = 0.19f), shape = RoundedCornerShape(4.dp))
                .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 4.dp)
                .height(55.dp) // Adjust the height as needed
        ) {
            TextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    }
                    .padding(start = 4.dp),
                textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent, // Change the background color here
                    focusedIndicatorColor = Color.Transparent, // Remove the focused underline
                    unfocusedIndicatorColor = Color.Transparent // Remove the unfocused underline
                ),
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                    .background(Color.White.copy(alpha = 0.19f), shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp)
            ) {
                mGenders.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            mSelectedText = label
                            mExpanded = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .background(Color.Transparent)
                            .clickable { mSelectedText = label; mExpanded = false }
                    ) {
                        Text(
                            text = label,
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

        }

            Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp) // Adjust the padding values for the Box
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))

            ) {
                TextButton(
                    onClick = { /* Handle button click here */ },
                    modifier = Modifier.padding(horizontal = 30.dp, vertical = 0.5.dp), // Adjust the padding values for the TextButton
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





    }
}

//TODO: Add a button to submit the form and navigate to the next screen and store the data
