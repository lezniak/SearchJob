package com.example.searchjob.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.searchjob.infrastructure.navigation.AddJobScreen
import com.example.searchjob.infrastructure.navigation.AppNavHost
import com.example.searchjob.infrastructure.navigation.ListScreen
import com.example.searchjob.infrastructure.navigation.allDestinationAfterRegister
import com.example.searchjob.infrastructure.navigation.navigateSingleTopTo
import com.example.searchjob.infrastructure.utils.BottomNavBar
import com.example.searchjob.infrastructure.utils.FAB
import com.example.searchjob.infrastructure.utils.MyAppBar
import com.example.searchjob.infrastructure.utils.SnackBarStatic
import com.example.searchjob.ui.theme.SearchJobTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SearchJobTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = allDestinationAfterRegister.find { it.route == currentDestination?.route }
    val snackbarHostState = remember { SnackbarHostState() }
    SnackBarStatic.snackbarStatic = snackbarHostState

    Scaffold(snackbarHost = {  SnackbarHost(snackbarHostState)},
        topBar = {
            if (currentScreen != null)
                MyAppBar(currentScreen.name)
        },
        bottomBar = {
            if (currentScreen != null){
                BottomNavBar(currentScreen.route) {
                    navController.navigateSingleTopTo(it)
                }
            }
        },
        floatingActionButton = {
            if (currentScreen == ListScreen){
                FAB {
                    navController.navigateSingleTopTo(AddJobScreen.route)
                }
            }
        })
    { innerPadding ->
        AppNavHost(navController = navController,snackbarHostState, modifier = Modifier.padding(innerPadding))
    }
}