package com.example.aplikasie_orange.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.HutangData
import com.example.aplikasie_orange.model.HutangViewModel

@Composable
fun ShowHutangScreen(
    navController: NavController,
    hutangViewModel: HutangViewModel,
) {
    var isTableVisible by remember { mutableStateOf(false) }
    val hutangList by hutangViewModel.getAllData().collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Back button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
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
                Text(text = if (isTableVisible) "Hide Table" else "Show Histori Tabel")
            }

            // Show the table when the button is clicked
            if (isTableVisible) {
                HutangTableScreen(hutangList = hutangList)
            }
        }
    }
}

@Composable
fun HutangTableScreen(hutangList: List<HutangData>) {
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
            Text("Nama", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Waktu", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Jumlah", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text("Note", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
        }

        // Data rows
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(0.dp)
        ) {
            items(hutangList) { hutang ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.DarkGray)
                        .padding(8.dp)
                ) {
                    Text(hutang.idHutang, modifier = Modifier.weight(1f))
                    Text(hutang.nama, modifier = Modifier.weight(1f))
                    Text(hutang.date, modifier = Modifier.weight(1f))
                    Text(hutang.uangHutang.toString(), modifier = Modifier.weight(1f))
                    Text(hutang.note, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}