package com.example.aplikasie_orange.ui.income

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.HutangData
import com.example.aplikasie_orange.data.IncomeData
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavDelIncome(
    navController: NavController,
    incomeViewModel: IncomeViewModel,
) {
    var idIncome: String by remember { mutableStateOf("") }
    var date: String by remember { mutableStateOf("") }
    var uangmasuk: Double by remember { mutableStateOf(0.0) }
    var note: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // Back button
        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = {navController.navigate((lyr.IncomeScreen.route))}
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = idIncome,
            onValueChange = {
                idIncome = it
            },
            label = {
                Text(text = "ID")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = date,
            onValueChange = {
                date = it
            },
            label = {
                Text(text = "DD/MM/YYYY")
            }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uangmasuk.toString(),
            onValueChange = {
                uangmasuk = it.toDoubleOrNull() ?: .0
            },
            label = {
                Text(text = "Jumlah")
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = note,
            onValueChange = {
                note = it
            },
            label = {
                Text(text = "Note")
            }
        )

        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            onClick = {
                val incomeData = IncomeData(
                    idIncome = idIncome,
                    date = date,
                    uangmasuk = uangmasuk,
                    note = note
                )

                incomeViewModel.saveData(incomeData = incomeData, context = context)
            }
        ) {
            Text(text = "Save")
        }

        // Delete Button
        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            onClick = {
                incomeViewModel.delateData(
                    idIncome = idIncome,
                    context = context,
                    navController = navController
                )
            }
        ) {
            Text(text = "Delete")
        }

        // Display all data in a LazyColumn

    }

}

