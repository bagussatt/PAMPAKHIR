package com.example.aplikasie_orange.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.HutangData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

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
    fun saveData(
        hutangData: HutangData,
        context: Context
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Hutang")
            .document(hutangData.idHutang)

        try{
            fireStoreRef.set(hutangData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Succsesfully saved data", Toast.LENGTH_LONG).show()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun retrieveData(
        idHutang: String,
        context: Context,
        data: (HutangData) -> Unit
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Hutang")
            .document(idHutang)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val hutangData = it.toObject<HutangData>()!!
                        data(hutangData)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun delateData(
        idHutang: String,
        context: Context,
        navController: NavController,
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Hutang")
            .document(idHutang)

        try{
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Succsesfully delate data", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
