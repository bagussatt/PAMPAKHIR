package com.example.aplikasie_orange.model

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LogSUViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
    }
}