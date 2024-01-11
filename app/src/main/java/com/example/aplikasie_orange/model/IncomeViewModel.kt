package com.example.aplikasie_orange.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.aplikasie_orange.data.IncomeData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class IncomeViewModel (): ViewModel(){

    fun getAllData(): kotlinx.coroutines.flow.Flow<List<IncomeData>> = callbackFlow {
        val fireStoreRef = Firebase.firestore.collection("Income")

        val subscription = fireStoreRef.addSnapshotListener { value, error ->
            if (error != null) {
                // Handle error
                close(error)
                return@addSnapshotListener
            }

            if (value != null) {
                val dataList = mutableListOf<IncomeData>()
                for (doc in value.documents) {
                    val incomeData = doc.toObject(IncomeData::class.java)
                    if (incomeData != null) {
                        dataList.add(incomeData)
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
    // Cancellation callback


    fun saveData(
        incomeData: IncomeData,
        context: Context
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Income")
            .document(incomeData.idIncome)

        try{
            fireStoreRef.set(incomeData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Succsesfully saved data", Toast.LENGTH_LONG).show()
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        idIncome: String,
        context: Context,
        data: (IncomeData) -> Unit
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Income")
            .document(idIncome)

        try{
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val incomeData = it.toObject<IncomeData>()!!
                        data(incomeData)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun delateData(
        idIncome: String,
        context: Context,
        navController: NavController,
    )    = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("Income")
            .document(idIncome)

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