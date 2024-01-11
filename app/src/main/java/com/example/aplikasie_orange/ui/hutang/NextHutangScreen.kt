package com.example.aplikasie_orange.ui.hutang

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.HutangData
import com.example.aplikasie_orange.model.HutangViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextHutangScreen(
    navController: NavController,
    hutangViewModel: HutangViewModel,
) {
    var idHutang: String by remember { mutableStateOf("") }
    var nama: String by remember { mutableStateOf("") }
    var date: String by remember { mutableStateOf("") }
    var uangHutang: Double by remember { mutableStateOf(0.0) }
    var note: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    // main Layout
    Column(modifier = Modifier.fillMaxSize()) {
        // Back button
        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = {navController.navigate((lyr.HutangScreen.route))}
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = idHutang,
            onValueChange = {
                idHutang = it
            },
            label = {
                Text(text = "ID Penghutang")
            }
        )
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
                    Text(text = "DD/MM/YYYY")
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uangHutang.toString(),
                onValueChange = {
                    uangHutang = it.toDoubleOrNull() ?: .0
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
                    val hutangData = HutangData(
                        idHutang = idHutang,
                        nama = nama,
                        date = date,
                        uangHutang = uangHutang,
                        note = note
                    )

                    hutangViewModel.saveData(hutangData = hutangData, context = context)
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
                    hutangViewModel.delateData(
                        idHutang = idHutang,
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

