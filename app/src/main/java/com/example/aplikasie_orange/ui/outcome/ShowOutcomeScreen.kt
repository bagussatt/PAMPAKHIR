package com.example.aplikasie_orange.ui.outcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.OutcomeData
import com.example.aplikasie_orange.model.OutcomeViewModel
import com.example.aplikasie_orange.navigation.lyr

@Composable
fun ShowOutcomeScreen(
    navController: NavController,
    outcomeViewModel: OutcomeViewModel,
) {

    var isTableVisible by remember { mutableStateOf(false) }
    val outcomeList by outcomeViewModel.getAllData().collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            IconButton(
                modifier = Modifier.padding(16.dp),
                onClick = { navController.navigate((lyr.IncomeScreen.route)) }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back_button")
            }

            // Button to show/hide the table
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    isTableVisible = !isTableVisible
                }
            ) {
                Text(text = if (isTableVisible) "Hide Table" else "Show Histori Outcome")
            }

            // Show the table when the button is clicked
            if (isTableVisible) {
                OutcomeTableScreen(outcomeList = outcomeList)
            }
        }
    }
}

@Composable
fun OutcomeTableScreen(outcomeList: List<OutcomeData>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(8.dp)
        ) {
            Text("ID", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Waktu", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Jumlah", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Note", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        }

        // Data rows
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(0.dp)
        ) {
            items(outcomeList) { income ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.DarkGray)
                        .padding(8.dp)
                ) {
                    Text(income.idOutme, modifier = Modifier.weight(1f))
                    Text(income.date, modifier = Modifier.weight(1f))
                    Text(income.uangkeluar.toString(), modifier = Modifier.weight(1f))
                    Text(income.note, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
