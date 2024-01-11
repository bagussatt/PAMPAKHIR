package com.example.aplikasie_orange.ui.income

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CariIncomeScreen(
    navController: NavController,
    incomeViewModel: IncomeViewModel,
) {
    var idIncome by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var uangmasuk by remember { mutableStateOf(.0) }
    var note by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Full screen Column
    Column(
        modifier = Modifier
            .fillMaxSize() // Takes full screen space
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button
        IconButton(
            onClick = { navController.navigate((lyr.IncomeScreen.route)) }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
        }

        // ID Penghutang input field
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = idIncome,
            onValueChange = { idIncome = it },
            label = { Text(text = "ID Penghutang") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "DD/MM/YYYY") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uangmasuk.toString(),
            onValueChange = { uangmasuk = it.toDoubleOrNull() ?: .0 },
            label = { Text(text = "Jumlah") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
                .padding(start = 8.dp)
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = {
                incomeViewModel.retrieveData(
                    idIncome = idIncome,
                    context = context
                ) { data ->
                    date = data.date
                    uangmasuk = data.uangmasuk
                    note = data.note
                }
            }
        ) {
            Text(text = "Get Data")
        }
    }
}