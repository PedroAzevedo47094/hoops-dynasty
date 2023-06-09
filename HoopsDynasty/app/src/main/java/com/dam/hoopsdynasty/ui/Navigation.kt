package com.dam.hoopsdynasty.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dam.hoopsdynasty.ui.view.BuzzerView
import com.dam.hoopsdynasty.ui.view.HomeView
import com.dam.hoopsdynasty.ui.view.Register_Login.ManagerInfo
import com.dam.hoopsdynasty.ui.view.Register_Login.SelectTeam
import com.dam.hoopsdynasty.ui.view.homeOptions.CalendarView
import com.dam.hoopsdynasty.ui.view.homeOptions.GameView
import com.dam.hoopsdynasty.ui.view.homeOptions.RosterView
import com.dam.hoopsdynasty.ui.view.homeOptions.StandingsView
import com.dam.hoopsdynasty.ui.view.homeOptions.TradeView
import com.dam.hoopsdynasty.ui.viewmodel.MainViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val viewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            ManagerInfo(viewModel = viewModel, true, navController = navController, 2000)
        }
        composable(route = Screen.RegisterScreen.route) {
            ManagerInfo(viewModel = viewModel, false, navController = navController, 1000)
        }
        composable(
            route = Screen.SelectTeamScreen.route + "/{email}/{name}/{password}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },
                navArgument("name") {
                    type = NavType.StringType
                }, navArgument("password") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            SelectTeam(
                viewModel = viewModel,
                navController = navController,
                email = entry.arguments?.getString("email")!!,
                name = entry.arguments?.getString("name")!!,
                password = entry.arguments?.getString("password")!!
            )
        }
        composable(route = Screen.RosterScreen.route) {
            RosterView(mainViewModel = viewModel, navController = navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeView(mainViewModel = viewModel, navController = navController)
        }

        composable(route = Screen.TradeScreen.route) {
            TradeView(mainViewModel = viewModel, navController = navController)
        }

        composable(route = Screen.StandingsScreen.route) {
            StandingsView(mainViewModel = viewModel, navController = navController)
        }

        composable(route = Screen.CalendarScreen.route) {
            CalendarView(mainViewModel = viewModel, navController = navController)
        }

      /*  composable(route = Screen.BuzzerScreen.route){
            BuzzerView(
                mainViewModel = viewModel,
                navController = navController,
                )
        }*/


        composable(route = Screen.GameScreen.route){
            GameView(
                mainViewModel = viewModel,
                navController = navController,
                )
        }

    }
}
