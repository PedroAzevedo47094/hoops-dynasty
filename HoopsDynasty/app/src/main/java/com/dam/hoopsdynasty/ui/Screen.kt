package com.dam.hoopsdynasty.ui

sealed class Screen(val route: String){
    object LoginScreen : Screen("login")
    object RegisterScreen : Screen("register")
    object SelectTeamScreen : Screen("selectTeam")
    object RosterScreen : Screen("roster")

    object TradeScreen : Screen("trade")

    object StandingsScreen : Screen("standings")

    object CalendarScreen : Screen("calendar")

    object HomeScreen : Screen("home")

    object GameScreen : Screen("game")

    object BuzzerScreen : Screen("buzzer")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}