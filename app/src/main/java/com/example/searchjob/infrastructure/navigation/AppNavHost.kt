package com.example.searchjob.infrastructure.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchjob.screens.login.LoginScreen
import com.example.searchjob.screens.welcome.WelcomeScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Welcome.route,modifier){
        composable(Welcome.route){
            WelcomeScreen {
                navController.navigateSingleTopTo(Login.route)
            }
        }

        composable(Login.route){
            LoginScreen(snackbarHostState)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }