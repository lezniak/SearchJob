package com.example.searchjob.infrastructure.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.searchjob.screens.HomeScreen.HomeScreen
import com.example.searchjob.screens.addJob.AddJobScreen
import com.example.searchjob.screens.list.ListScreen
import com.example.searchjob.screens.login.LoginScreen
import com.example.searchjob.screens.register.RegisterScreen
import com.example.searchjob.screens.welcome.WelcomeScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


@Composable
fun AppNavHost(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val user = Firebase.auth.currentUser

    userState(navController)
    NavHost(navController = navController, startDestination = if(user != null) HomeScreen.route else Welcome.route,modifier){
        composable(Welcome.route){
            WelcomeScreen (onSignUpClick = {
                navController.navigateSingleTopTo(Register.route)
            }, onSignInClick = {
                navController.navigateSingleTopTo(LoginScreen.route)
            })
        }

        composable(Register.route){
            RegisterScreen(snackbarHostState) {
                navController.navigateAndClearHistory(HomeScreen.route)
            }
        }

        composable(HomeScreen.route){
            HomeScreen()
        }

        composable(LoginScreen.route){
            LoginScreen {
                navController.navigateSingleTopTo(HomeScreen.route)
            }
        }

        composable(ListScreen.route){
            ListScreen()
        }

        composable(AddJobScreen.route){
            AddJobScreen()
        }
    }
}
@Composable
fun userState(navController: NavHostController) {
    val currentOnAuthStateChanged by rememberUpdatedState { auth: FirebaseAuth ->
        if (auth.currentUser == null) {
            navController.navigateAndClearHistory(Welcome.route)
        }
    }

    DisposableEffect(Unit) {
        val authListener = FirebaseAuth.AuthStateListener(currentOnAuthStateChanged)
        FirebaseAuth.getInstance().addAuthStateListener(authListener)
        onDispose {
            FirebaseAuth.getInstance().removeAuthStateListener(authListener)
        }
    }

}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            //saveState = true
        }
        launchSingleTop = true
        //restoreState = true
    }

fun NavHostController.navigateAndClearHistory(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateAndClearHistory.graph.findStartDestination().id
        ) {
            inclusive = true
        }
    }