package com.example.aplikasie_orange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.model.OutcomeViewModel
import com.example.aplikasie_orange.navigation.PageNavigation
import com.example.aplikasie_orange.ui.HomeScreen
import com.example.aplikasie_orange.ui.HutangScreen
import com.example.aplikasie_orange.ui.IncomeScreen
import com.example.aplikasie_orange.ui.NextHutangScreen
import com.example.aplikasie_orange.ui.theme.AplikasiEOrangeTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {

    private val hutangViewModel: HutangViewModel by viewModels()
    private val incomeViewModel: IncomeViewModel by viewModels()
    private val outcomeViewModel: OutcomeViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firestore
        Firebase.firestore

        // Initialize the NavController


        setContent {
            AplikasiEOrangeTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    navController = rememberNavController()

                    PageNavigation(

                        navController = navController,
                        hutangViewModel = hutangViewModel,
                        incomeViewModel = incomeViewModel,
                        outcomeViewModel = outcomeViewModel,
                    )
                }
            }
        }
    }
}