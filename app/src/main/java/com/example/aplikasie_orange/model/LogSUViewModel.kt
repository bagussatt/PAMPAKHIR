package com.example.aplikasie_orange.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
    fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Authentication successful
                    onSuccess.invoke()
                } else {
                    // Authentication failed, log the error message
                    Log.e("LogSUViewModel", "signIn failed: ${task.exception?.message}")
                    onError.invoke("Authentication failed. Check your email and password.")
                }
            }
    }

    // Metode lainnya sesuai kebutuhan, seperti pengiriman email verifikasi, cek status otentikasi, dll.

    // Misalnya, untuk mendapatkan pengguna saat ini:
    fun getCurrentUser(): FirebaseUser? = auth.currentUser
}
