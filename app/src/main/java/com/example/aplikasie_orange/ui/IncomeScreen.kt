package com.example.aplikasie_orange.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import com.example.aplikasie_orange.data.IncomeData
import com.example.aplikasie_orange.model.IncomeViewModel

@Composable
fun IncomeScreen(
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
    }
}