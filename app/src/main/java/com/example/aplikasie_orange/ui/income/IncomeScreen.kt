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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aplikasie_orange.data.HutangData
import com.example.aplikasie_orange.data.IncomeData
import com.example.aplikasie_orange.model.IncomeViewModel
import com.example.aplikasie_orange.navigation.lyr
import com.example.aplikasie_orange.ui.theme.AplikasiEOrangeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    navController: NavController,
    incomeViewModel: IncomeViewModel
) {
    var idIncome by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var uangmasuk by remember { mutableStateOf(0.0) }
    var note by remember { mutableStateOf("") }

    val context = LocalContext.current

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

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            // Button to submit data
            Button(
                modifier = Modifier
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(onClick = { navController.navigate(lyr.ShowIncomeScreen.route) }) {
                    Text(text = "Histori")
                }

                Button(onClick = { navController.navigate(lyr.SavDelIncomeScreen.route) }) {
                    Text(text = "Edit dan Hapus ")
                }

                Button(onClick = { navController.navigate(lyr.CariIncomeScreen.route) }) {
                    Text(text = "Cari ")
            }
        }
    }
}



@Preview
@Composable
fun incomeprev() {
    AplikasiEOrangeTheme {
        IncomeScreen(
            navController = NavHostController(LocalContext.current),
            incomeViewModel = IncomeViewModel()
        )
    }
}