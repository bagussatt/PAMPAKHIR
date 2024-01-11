package com.example.aplikasie_orange.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.model.OutcomeViewModel
import com.example.aplikasie_orange.ui.HomeScreen
import com.example.aplikasie_orange.ui.HutangScreen
import com.example.aplikasie_orange.ui.IncomeScreen
import com.example.aplikasie_orange.ui.OutcomeScreen



@Composable
fun PageNavigation(
    navController: NavHostController ,
    hutangViewModel: HutangViewModel,
    incomeViewModel: IncomeViewModel,
    outcomeViewModel: OutcomeViewModel,
){
    NavHost(
        navController = navController,
        startDestination = lyr.HomeScreen.route
    ) {
        composable(
            route = lyr.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = lyr.IncomeScreen.route
        ) {
            IncomeScreen(navController = navController, incomeViewModel = incomeViewModel)
        }

        composable(
            route = lyr.HutangScreen.route
        ) {
            HutangScreen( navController= navController, hutangViewModel = hutangViewModel)
        }
            composable(
                route = lyr.OutcomeScreen.route
            ){
                OutcomeScreen(navController = navController, outcomeViewModel = outcomeViewModel)
            }

    }
}