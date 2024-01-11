package com.example.aplikasie_orange.navigation


sealed class lyr(val route: String) {
    object HomeScreen : lyr(route = "home")
    object IncomeScreen : lyr(route = "income")
    object OutcomeScreen : lyr(route = "outcome")
    object HutangScreen : lyr(route = "hutang")

    object CariHutangScreen : lyr(route = "cari_hutang")
    object CariIncomeScreen : lyr(route = "cari_income")
    object CariOutcomeScreen : lyr(route = "cari_outcome")

    object NextHutangScreen : lyr(route = "next_hutang")
    object ShowHutangScreen : lyr(route = "show_hutang")

}