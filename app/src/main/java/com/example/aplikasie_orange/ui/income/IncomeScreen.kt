package com.example.aplikasie_orange.ui.income

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.HutangData
import com.example.aplikasie_orange.data.IncomeData
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    navController: NavController,
    incomeViewModel: IncomeViewModel,

    ) {
    var idIncome: String by remember { mutableStateOf("") }
    var date: String by remember { mutableStateOf("") }
    var uangmasuk: Double by remember { mutableDoubleStateOf(0.0) }
    var note: String by remember { mutableStateOf("") }

    val context = LocalContext.current //Akses konteks saat ini

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // OutlinedTextField for ID Income
        OutlinedTextField(
            value = idIncome,
            onValueChange = { idIncome = it },
            label = { Text("ID Income") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Date
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Uang Masuk
        OutlinedTextField(
            value = uangmasuk.toString(),
            onValueChange = {
                uangmasuk = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Uang Masuk") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Note
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Button to submit data
            // save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val incomeData = IncomeData(
                        idIncome = idIncome,
                        date = date,
                        uangmasuk = uangmasuk,
                        note = note,
                    )

                    incomeViewModel.saveData(incomeData = incomeData, context = context)
                }
            ) {
                Text(text = "Masukan Data")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { navController.navigate(lyr.ShowHutangScreen.route) }) {
                Text(text = "Histori Hutang")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { navController.navigate(lyr.NextHutangScreen.route) }) {
                Text(text = "Edit dan Hapus Hutang")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { navController.navigate(lyr.CariHutangScreen.route) }) {
                Text(text = "Cari Hutang")
            }
        }
    }
}