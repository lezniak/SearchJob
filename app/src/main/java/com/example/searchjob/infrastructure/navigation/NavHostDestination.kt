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

data object Register : AppDestination{
    override val route: String
        get() = "register"
    override val name: String
        get() = "Register"
}

data object HomeScreen : AppDestination {
    override val route: String
        get() = "home"
    override val name: String
        get() = "Home"

}


data object LoginScreen : AppDestination {
    override val route: String
        get() = "login"
    override val name: String
        get() = "Login"
}

val allDestinationBeforeRegister = listOf(Register,Welcome)
val allDestinationAfterRegister = listOf(HomeScreen)