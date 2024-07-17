package com.example.searchjob.infrastructure.navigation

sealed interface AppDestination {
    val route : String
}


data object Welcome : AppDestination{
    override val route: String
        get() = "welcome"
}

data object Login : AppDestination{
    override val route: String
        get() = "login"
}


val allDestination = listOf(Login,Welcome)