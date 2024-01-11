package com.example.aplikasie_orange.ui.outcome

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
import com.example.aplikasie_orange.model.OutcomeViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CariOutcomeScreen(
    navController: NavController,
    outcomeViewModel: OutcomeViewModel,
) {
    var idOutme by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var uangkeluar by remember { mutableStateOf(0.0) }
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
            onClick = { navController.navigate((lyr.OutcomeScreen.route)) }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
        }

        // ID Penghutang input field
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = idOutme,
            onValueChange = { idOutme = it },
            label = { Text(text = "ID ") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "Waktu") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uangkeluar.toString(),
            onValueChange = { uangkeluar = it.toDoubleOrNull() ?: .0 },
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
                outcomeViewModel.retrieveData(
                    idOutme = idOutme,
                    context = context
                ) { data ->
                    date = data.date
                    uangkeluar = data.uangkeluar
                    note = data.note
                }
            }
        ) {
            Text(text = "Get Data")
        }
    }
}