package com.example.aplikasie_orange.ui.hutang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CariHutangScreen(
    navController: NavController,
    hutangViewModel: HutangViewModel,
) {
    var idHutang by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var uangHutang by remember { mutableStateOf(0.0) }
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
            onClick = { navController.navigate((lyr.HutangScreen.route)) }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
        }

        // ID Penghutang input field
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = idHutang,
            onValueChange = { idHutang = it },
            label = { Text(text = "ID Penghutang") }
        )

        // Get Data Button

        // Other input fields
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = "Nama") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "DD/MM/YYYY") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uangHutang.toString(),
            onValueChange = { uangHutang = it.toDoubleOrNull() ?: .0 },
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
                hutangViewModel.retrieveData(
                    idHutang = idHutang,
                    context = context
                ) { data ->
                    nama = data.nama
                    date = data.date
                    uangHutang = data.uangHutang
                    note = data.note
                }
            }
        ) {
            Text(text = "Get Data")
        }
    }
}