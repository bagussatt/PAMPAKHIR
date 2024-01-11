package com.example.aplikasie_orange.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.aplikasie_orange.R
import com.example.aplikasie_orange.navigation.lyr
import com.example.aplikasie_orange.ui.theme.AplikasiEOrangeTheme

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val image = painterResource(id = R.drawable.orange) // Change the image resource accordingly

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 120.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(150.dp) // Adjust the size of the image
                )
                Text(
                    text = "E-Orange",
                    color = Color.Yellow, // Change the color
                    fontFamily = FontFamily.Cursive,
                    fontSize = 35.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Your Online Finance Book",
                    color = Color.Yellow, // Change the color
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            IconButton(
                onClick = { navController.navigate((lyr.IncomeScreen.route)) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_monetization_on_24), // Add your income icon
                    contentDescription = "Income"
                )
                Text("Income", modifier = Modifier.alpha(0f)) // Hide text
            }

            IconButton(
                onClick = { navController.navigate(lyr.OutcomeScreen.route) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_money_off_24), // Add your outcome icon
                    contentDescription = "Outcome"
                )
                Text("Outcome", modifier = Modifier.alpha(0f)) // Hide text
            }

            IconButton(
                onClick = { navController.navigate(lyr.HutangScreen.route) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_handshake_24), // Add your hutang icon
                    contentDescription = "Hutang"
                )
                Text("Hutang", modifier = Modifier.alpha(0f)) // Hide text
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEOrangeHome() {
    AplikasiEOrangeTheme{
        HomeScreen(navController = rememberNavController())
    }
}
