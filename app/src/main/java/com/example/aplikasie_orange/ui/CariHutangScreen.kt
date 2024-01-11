package com.example.aplikasie_orange.ui

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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

    // User ID
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(end = 8.dp),
            value = idHutang,
            onValueChange = {
                idHutang = it
            },
            label = {
                Text(text = "ID Penghutang")
            }
        )

        // Get user data Button
        Button(
            modifier = Modifier
                .padding(start = 8.dp)
                .width(100.dp),
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

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = nama,
        onValueChange = {
            nama = it
        },
        label = {
            Text(text = "Nama")
        }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = date,
        onValueChange = {
            date = it
        },
        label = {
            Text(text = "Waktu")
        }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = uangHutang.toString(),
        onValueChange = {
            uangHutang = it.toDoubleOrNull() ?: 0.0
        },
        label = {
            Text(text = "Jumlah")
        }
    )
}
