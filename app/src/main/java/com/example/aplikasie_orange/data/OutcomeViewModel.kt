package com.example.aplikasie_orange.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

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
    fun saveData(
        outcomeData: OutcomeData,
        context: Context
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Outcome")
            .document(outcomeData.idOutme)

        try{
            fireStoreRef.set(outcomeData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Succsesfully saved data", Toast.LENGTH_LONG).show()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun retrieveData(
        idOutme: String,
        context: Context,
        data: (OutcomeData) -> Unit
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Outcome")
            .document(idOutme)

        try{
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val outcomeData = it.toObject<OutcomeData>()!!
                        data(outcomeData)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}