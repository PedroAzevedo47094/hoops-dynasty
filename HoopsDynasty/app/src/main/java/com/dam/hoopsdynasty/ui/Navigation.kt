package com.dam.hoopsdynasty.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam.hoopsdynasty.ui.view.Register_Login.ManagerInfo
import com.dam.hoopsdynasty.ui.view.Register_Login.ManagerLogin
import com.dam.hoopsdynasty.ui.view.Register_Login.ManagerRegister
import com.dam.hoopsdynasty.ui.view.SelectTeam
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val viewModel : MainViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            ManagerInfo(viewModel = viewModel, true, navController = navController, 2000)
        }
        composable(route = Screen.RegisterScreen.route) {
            ManagerInfo(viewModel = viewModel, false,navController = navController, 1000)
        }
        composable(route = Screen.SelectTeamScreen.route) {
            SelectTeam(viewModel = viewModel, navController = navController)
        }
    }
}
