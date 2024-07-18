package com.example.searchjob.infrastructure.navigation

sealed interface AppDestination {
    val route : String
    val name : String
}


data object Welcome : AppDestination{
    override val route: String
        get() = "welcome"
    override val name: String
        get() = "Welcome"
}

data object Login : AppDestination{
    override val route: String
        get() = "login"
    override val name: String
        get() = "Login"
}

data object HomeScreen : AppDestination {
    override val route: String
        get() = "home"
    override val name: String
        get() = "Home"

}

val allDestinationBeforeRegister = listOf(Login,Welcome)
val allDestinationAfterRegister = listOf(HomeScreen)