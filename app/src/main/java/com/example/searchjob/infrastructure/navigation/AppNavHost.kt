package com.example.searchjob.infrastructure.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchjob.screens.login.LoginScreen
import com.example.searchjob.screens.welcome.WelcomeScreen


@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Welcome.route,modifier){
        composable(Welcome.route){
            WelcomeScreen()
        }

        composable(Login.route){
            LoginScreen()
        }
    }
}