package com.dam.hoopsdynasty.ui.view.Register_Login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.hoopsdynasty.R
import com.dam.hoopsdynasty.ui.Screen
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel


@Composable
fun ManagerRegister(viewModel: MainViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }

    Spacer(modifier = Modifier.height(40.dp))
    Column() {
        Box(
            modifier = Modifier
                .size(70.dp)

        ) {
            Image(
                painter = painterResource(R.drawable.hoopsdynasty),
                contentDescription = "logo",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

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
                    .border((0.7.dp), colorScheme.primary, shape = RoundedCornerShape(8.dp)),
            ) {
                Text(
                    text = "Register Manager",
                    modifier = Modifier
                        .padding(horizontal = 40.dp, vertical = 10.dp),
                    color = colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Column() {
            Text(
                text = "Email:",
                color = colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.19f), shape = RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke((0.7.dp), colorScheme.primary),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 4.dp)
                    .height(55.dp) // Adjust the height as needed
            ) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, // Change the background color here
                        focusedIndicatorColor = Color.Transparent, // Remove the focused underline
                        unfocusedIndicatorColor = Color.Transparent // Remove the unfocused underline
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Column() {
            Text(
                text = "Manager Name:",
                color = colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.19f), shape = RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke((0.7.dp), colorScheme.primary),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 4.dp)
                    .height(55.dp) // Adjust the height as needed
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, // Change the background color here
                        focusedIndicatorColor = Color.Transparent, // Remove the focused underline
                        unfocusedIndicatorColor = Color.Transparent // Remove the unfocused underline
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Column() {
            Text(
                text = "Password:",
                color = colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.19f), shape = RoundedCornerShape(4.dp))
                    .border(
                        BorderStroke((0.7.dp), colorScheme.primary),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 4.dp)
                    .height(55.dp) // Adjust the height as needed
            ) {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp),
                    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, // Change the background color here
                        focusedIndicatorColor = Color.Transparent, // Remove the focused underline
                        unfocusedIndicatorColor = Color.Transparent // Remove the unfocused underline
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))



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
                    .border((0.7.dp), colorScheme.primary, shape = RoundedCornerShape(8.dp))
            ) {
                TextButton(
                    onClick = {
                        if (name.isNotBlank()) {
                            navController.navigate(
                                Screen.SelectTeamScreen.withArgs(
                                    email,
                                    name,
                                    password
                                )
                            )
                        }
                    },
                    modifier = Modifier.padding(
                        horizontal = 30.dp,
                        vertical = 0.5.dp
                    ), // Adjust the padding values for the TextButton
                    colors = ButtonDefaults.textButtonColors(
                        // backgroundColor = Color.Transparent, // Set the background color to transparent
                        contentColor = colorScheme.primary // Set the text color
                    ),
                    shape = RoundedCornerShape(8.dp), // Apply rounded corner shape
                    contentPadding = PaddingValues(0.dp) // Remove padding from the button
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Next",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Light
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Arrow Forward",
                            modifier = Modifier.padding(start = 4.dp),

                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Already have an account? Login")
                    }
                },
                color = colorScheme.primary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route)
                    }
            )
        }
    }


}


