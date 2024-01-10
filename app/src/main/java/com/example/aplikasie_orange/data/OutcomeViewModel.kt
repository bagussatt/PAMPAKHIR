package com.example.aplikasie_orange.data

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class OutcomeViewModel : ViewModel() {
    fun getAllData(): kotlinx.coroutines.flow.Flow<List<OutcomeData>> = callbackFlow {
        val fireStoreRef = Firebase.firestore.collection("Outcome")

        val subscription = fireStoreRef.addSnapshotListener { value, error ->
            if (error != null) {
                // Handle error
                close(error)
                return@addSnapshotListener
            }

            if (value != null) {
                val dataList = mutableListOf<OutcomeData>()
                for (doc in value.documents) {
                    val outcomeData = doc.toObject(OutcomeData::class.java)
                    if (outcomeData != null) {
                        dataList.add(outcomeData)
                    }
                }
                trySend(dataList)
            }
        }

        // Cancellation callback
        awaitClose {
            subscription.remove()
        }
    }

}