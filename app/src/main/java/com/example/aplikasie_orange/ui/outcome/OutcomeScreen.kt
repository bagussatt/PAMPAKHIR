package com.example.aplikasie_orange.ui.outcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.OutcomeData
import com.example.aplikasie_orange.model.OutcomeViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutcomeScreen(
        navController: NavController,
        outcomeViewModel: OutcomeViewModel,

        ) {
        var idOutme: String by remember { mutableStateOf("") }
        var date: String by remember { mutableStateOf("") }
        var uangkeluar: Double by remember { mutableDoubleStateOf(0.0) }
        var note: String by remember { mutableStateOf("") }

        val context = LocalContext.current //Akses konteks saat ini

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                modifier = Modifier.padding(16.dp),
                onClick = { navController.navigate((lyr.HomeScreen.route)) }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
            }

            // OutlinedTextField for ID
            OutlinedTextField(
                value = idOutme,
                onValueChange = { idOutme = it },
                label = { Text("ID") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // OutlinedTextField for Date
            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("DD/MM/YYYY") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // OutlinedTextField for UangKeluar
            OutlinedTextField(
                value = uangkeluar.toString(),
                onValueChange = {
                    uangkeluar = it.toDoubleOrNull() ?: .0
                },
                label = { Text("Outcome") },
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

            // Button to submit data
            // save Button
            Button(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                onClick = {
                    val outcomeData = OutcomeData(
                        idOutme = idOutme,
                        date = date,
                        uangkeluar = uangkeluar,
                        note = note,
                    )

                    outcomeViewModel.saveData(outcomeData = outcomeData, context = context)
                }
            ) {
                Text(text = "Masukan Data")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(onClick = { navController.navigate(lyr.ShowOutcomeScreen.route) }) {
                    Text(text = "Histori")
                }

                Button(onClick = { navController.navigate(lyr.SavDelOutcome.route) }) {
                    Text(text = "Edit dan Hapus ")
                }

                Button(onClick = { navController.navigate(lyr.CariOutcomeScreen.route) }) {
                    Text(text = "Cari")
                }
            }
        }
    }
