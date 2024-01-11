package com.example.aplikasie_orange.ui.hutang

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aplikasie_orange.data.HutangData
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.navigation.lyr
import com.example.aplikasie_orange.ui.theme.AplikasiEOrangeTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HutangScreen(
    navController: NavController,
    hutangViewModel: HutangViewModel,
) {
    var idHutang: String by remember { mutableStateOf("") }
    var nama: String by remember { mutableStateOf("") }
    var date: String by remember { mutableStateOf("") }
    var uangHutang: Double by remember { mutableDoubleStateOf(0.0) }
    var note: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Button
        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = { navController.navigate((lyr.HomeScreen.route)) }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
        }

        // OutlinedTextField for ID
        OutlinedTextField(
            value = idHutang,
            onValueChange = { idHutang = it },
            label = { Text("ID Outcome") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Nama Penghutang
        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama Penghutang") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for Date
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Waktu") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OutlinedTextField for UangHutang
        OutlinedTextField(
            value = uangHutang.toString(),
            onValueChange = {
                uangHutang = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Uang Hutang") },
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = {
                val hutangData = HutangData(
                    idHutang = idHutang,
                    nama = nama,
                    date = date,
                    uangHutang = uangHutang,
                    note = note,
                )

                hutangViewModel.saveData(hutangData = hutangData, context = context)
            }
        ) {
            Text(text = "Masukan Hutang")
        }

        // Button Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Histori Hutang Button
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onClick = { navController.navigate((lyr.ShowHutangScreen.route)) }
            ) {
                Text(text = "Histori")
            }

            // Edit dan Hapus Hutang Button
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                onClick = { navController.navigate((lyr.NextHutangScreen.route)) }
            ) {
                Text(text = "Edit dan Hapus")
            }

            // Cari Hutang Button
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                onClick = { navController.navigate((lyr.CariHutangScreen.route)) }
            ) {
                Text(text = "Cari")
            }
        }
    }
}

@Preview
@Composable
fun HutangScreenPreview() {
    AplikasiEOrangeTheme {
        HutangScreen(navController = rememberNavController(), hutangViewModel = HutangViewModel())
    }
}
