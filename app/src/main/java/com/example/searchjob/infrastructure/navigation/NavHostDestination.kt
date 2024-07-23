package com.example.searchjob.infrastructure.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface AppDestination {
    val route : String
    val name : String
    val icon : ImageVector?
}


data object Welcome : AppDestination{
    override val route: String
        get() = "welcome"
    override val name: String
        get() = "Welcome"
    override val icon: ImageVector?
        get() = null
}

data object Register : AppDestination{
    override val route: String
        get() = "register"
    override val name: String
        get() = "Register"
    override val icon: ImageVector?
        get() = null
}

data object HomeScreen : AppDestination {
    override val route: String
        get() = "home"
    override val name: String
        get() = "Home"
    override val icon: ImageVector?
        get() = Icons.Default.Home

}

data object ListScreen : AppDestination {
    override val route: String
        get() = "myjobs"
    override val name: String
        get() = "My jobs"
    override val icon: ImageVector?
        get() = Icons.Default.Person

}

data object LoginScreen : AppDestination {
    override val route: String
        get() = "login"
    override val name: String
        get() = "Login"
    override val icon: ImageVector?
        get() = null
}

data object AddJobScreen: AppDestination{
    override val route: String
        get() = "add_job"
    override val name: String
        get() = "Add Job"
    override val icon: ImageVector?
        get() = null

}

val allDestinationBeforeRegister = listOf(Register,Welcome)
val allDestinationAfterRegister = listOf(HomeScreen,ListScreen,AddJobScreen)