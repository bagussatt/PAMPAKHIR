package com.example.aplikasie_orange.data

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class HutangViewModel: ViewModel() {
    fun getAllData(): kotlinx.coroutines.flow.Flow<List<HutangData>> = callbackFlow {
        val fireStoreRef = Firebase.firestore.collection("Hutang")

        val subscription = fireStoreRef.addSnapshotListener { value, error ->
            if (error != null) {
                // Handle error
                close(error)
                return@addSnapshotListener
            }

            if (value != null) {
                val dataList = mutableListOf<HutangData>()
                for (doc in value.documents) {
                    val hutangData = doc.toObject(HutangData::class.java)
                    if (hutangData != null) {
                        dataList.add(hutangData)
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