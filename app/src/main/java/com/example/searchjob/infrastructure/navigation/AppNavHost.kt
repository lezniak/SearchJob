package com.example.searchjob.infrastructure.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchjob.screens.HomeScreen.HomeScreen
import com.example.searchjob.screens.login.LoginScreen
import com.example.searchjob.screens.welcome.WelcomeScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun AppNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val user = Firebase.auth.currentUser
    NavHost(navController = navController, startDestination = if(user != null) HomeScreen.route else Welcome.route,modifier){
        composable(Welcome.route){
            WelcomeScreen {
                navController.navigateSingleTopTo(Login.route)
            }
        }

        composable(Login.route){
            LoginScreen(snackbarHostState) {
                navController.navigateAndClearHistory(HomeScreen.route)
            }
        }

        composable(HomeScreen.route){
            HomeScreen()
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

fun NavHostController.navigateAndClearHistory(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateAndClearHistory.graph.findStartDestination().id
        ) {
            inclusive = true
        }
    }