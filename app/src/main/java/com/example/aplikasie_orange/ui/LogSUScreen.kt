package com.example.aplikasie_orange.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aplikasie_orange.R
import com.example.aplikasie_orange.model.LogSUViewModel
import com.example.aplikasie_orange.navigation.lyr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogSUScreen(navController: NavController,
                logSUViewModel: LogSUViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var showToastMessage by remember { mutableStateOf<String?>(null) }

    val image = painterResource(id = R.drawable.orange)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login dan Daftar Akun",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp) // Adjust the size of the image
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // Input Email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Input Password
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Sign Up
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Button Sign Up
            Button(
                onClick = { logSUViewModel.createUser(email, password) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp) // Add padding between buttons
            ) {
                Text("Sign Up")
            }

            // Button Sign In
            Button(
                onClick = {
                    logSUViewModel.signIn(email, password,
                        onSuccess = {
                            // Authentication successful, navigate to the desired screen
                            navController.navigate(lyr.HomeScreen.route)
                        },
                        onError = { errorMessage ->
                            // Show an error message to the user
                            showToastMessage = errorMessage
                        })
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp) // Add padding between buttons
            ) {
                Text("Sign In")
            }
        }
        }
    }