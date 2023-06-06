package com.dam.hoopsdynasty.ui

sealed class Screen(val route: String){
    object LoginScreen : Screen("login")
    object RegisterScreen : Screen("register")
}