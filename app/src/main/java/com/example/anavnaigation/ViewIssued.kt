package com.example.anavnaigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class ViewIssued : Fragment() {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val docRef: DocumentReference = db.collection("user").document()
    private val collectionRef: CollectionReference = db.collection("user")
    private val QuantityDocRef: DocumentReference = db.collection("quantity").document()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_issued, container, false)
        val equip= view?.findViewById<RecyclerView>(R.id.issuedList)
        val equipmentRollList: MutableList<EquipmentRollPair> = mutableListOf()





        fun onSuccess(quantityValue: Int?) {

        }
        fetchDocuments(object : QuantityCallback {
            override fun onSuccess(quantityValue: Int?) {

            }
            override fun onSuccessEquipmentRollPair(pair: EquipmentRollPair) {
                equipmentRollList.add(pair)
                equip?.adapter?.notifyDataSetChanged()
            }


            override fun onSuccess2(quantityValue: EquipmentRollPair, rollValue: String?,) {

            }

            override fun onFailure() {
                // Handle failure (optional)
            }
        })


        val navController = findNavController()
        equip?.layoutManager = LinearLayoutManager(context)
        equip?.adapter = context?.let { Adapter2(it,equipmentRollList) }
        return view

    }

    private fun fetchDocuments(callback: QuantityCallback) {

        val collectionRef = db.collection("user")

        // Fetch documents from the collection
        collectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                for (documentSnapshot in querySnapshot) {
                    val equipmentValue = documentSnapshot.getString("equipment")
                    val rollValue = documentSnapshot.getString("roll")

                    if (equipmentValue != null) {
                        // Invoke the callback with the quantity value
                        callback.onSuccessEquipmentRollPair(EquipmentRollPair(equipmentValue,rollValue.toString()))
                    } else {
                        }
                    }

            }
            .addOnFailureListener { exception ->
                // Handle errors
                callback.onFailure()
            }
    }


}