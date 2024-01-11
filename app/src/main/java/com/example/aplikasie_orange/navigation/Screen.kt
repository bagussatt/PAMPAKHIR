package com.example.aplikasie_orange.navigation


sealed class lyr(val route: String) {
    object HomeScreen : lyr(route = "home")
    object IncomeScreen : lyr(route = "income")
    object OutcomeScreen : lyr(route = "outcome")
    object HutangScreen : lyr(route = "hutang")

}