package com.example.aplikasie_orange.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.model.LogSUViewModel
import com.example.aplikasie_orange.model.OutcomeViewModel
import com.example.aplikasie_orange.ui.hutang.CariHutangScreen
import com.example.aplikasie_orange.ui.HomeScreen
import com.example.aplikasie_orange.ui.LogSUScreen
import com.example.aplikasie_orange.ui.hutang.HutangScreen
import com.example.aplikasie_orange.ui.income.IncomeScreen
import com.example.aplikasie_orange.ui.hutang.NextHutangScreen
import com.example.aplikasie_orange.ui.outcome.OutcomeScreen
import com.example.aplikasie_orange.ui.hutang.ShowHutangScreen
import com.example.aplikasie_orange.ui.income.CariIncomeScreen
import com.example.aplikasie_orange.ui.income.SavDelIncome
import com.example.aplikasie_orange.ui.income.ShowIncomeScreen
import com.example.aplikasie_orange.ui.outcome.CariOutcomeScreen
import com.example.aplikasie_orange.ui.outcome.SavDelOutcome
import com.example.aplikasie_orange.ui.outcome.ShowOutcomeScreen


@Composable
fun PageNavigation(
    navController: NavHostController ,
    hutangViewModel: HutangViewModel,
    incomeViewModel: IncomeViewModel,
    outcomeViewModel: OutcomeViewModel,
){
    NavHost(
        navController = navController,
        startDestination = lyr.LogSUScreen.route
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
        composable(
            route = lyr.CariHutangScreen.route
        ){
            CariHutangScreen(navController = navController, hutangViewModel = hutangViewModel)
        }
        composable(
            route = lyr.NextHutangScreen.route
        ){
            NextHutangScreen(navController = navController, hutangViewModel = hutangViewModel)
        }
        composable(
            route = lyr.ShowHutangScreen.route
        ){
            ShowHutangScreen(navController = navController, hutangViewModel = hutangViewModel)
        }
        composable(
            route = lyr.ShowIncomeScreen.route
        ){
            ShowIncomeScreen(navController = navController, incomeViewModel = incomeViewModel)
        }
        composable(
            route = lyr.SavDelIncomeScreen.route
        )
        {
            SavDelIncome(navController = navController, incomeViewModel = incomeViewModel)
        }
        composable(
            route = lyr.CariOutcomeScreen.route
        ){
            CariOutcomeScreen(navController = navController, outcomeViewModel = outcomeViewModel)
        }
        composable(
            route = lyr.SavDelOutcome.route
        ){
            SavDelOutcome(navController = navController, outcomeViewModel = outcomeViewModel)
        }
        composable(
            route = lyr.ShowOutcomeScreen.route
        ){
            ShowOutcomeScreen(navController = navController, outcomeViewModel = outcomeViewModel)
        }
        composable(
            route = lyr.CariIncomeScreen.route
        ){
            CariIncomeScreen(navController = navController, incomeViewModel = incomeViewModel)
        }
        composable(
            route = lyr.LogSUScreen.route
        ) {
            LogSUScreen(navController = navController, logSUViewModel = LogSUViewModel())
        }
    }
}