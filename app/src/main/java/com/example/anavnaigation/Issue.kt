package com.example.anavnaigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore



class Issue : Fragment() {

    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var editRoll: EditText
    private lateinit var btnIssue: Button
    private lateinit var btnReturn: Button
    private var selectedOption: String? = null

    private val Roll = "roll"
    private val Equipment = "equipment"
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
        val view = inflater.inflate(R.layout.fragment_issue, container, false)

        val spinner: Spinner = view.findViewById(R.id.spinner)

        val items = arrayOf(
            "Badminton Racket"
             ,"Basketball"
                    ,"Carromboard"
                    ,"Chessboard"
                    ,"Cricket Ball"
                ,"Cricket Bat"
                ,"Football"
                  ,"Table Tennis Racket"
                    ,"Tennis Racket"
                    ,"Volleyball"
        )
        val quantity:List<String> = listOf("Quantity:4","Quantity:2","Quantity:3","Quantity:4","Quantity:2",
            "Quantity:3","Quantity:1","Quantity:4","Quantity:2","Quantity:2")

        val adapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {

                selectedOption = items[position]
                //selectedOption variable has the value of selected option
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


        editRoll = view.findViewById(R.id.roll)
        btnIssue = view.findViewById(R.id.Issue)
        btnReturn= view.findViewById(R.id.Return)

        btnIssue.setOnClickListener {
            issue()

        }
        btnReturn.setOnClickListener{
            submit()

        }

        return view


    }

    private fun submit() {
        val roll = editRoll.text.toString()
        val equipment = selectedOption.toString()

        if (roll.isNotEmpty() && equipment.isNotEmpty()) {
            collectionRef.whereEqualTo(Equipment, equipment)
                .whereEqualTo(Roll, roll)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val documents = task.result
                        if (documents != null && !documents.isEmpty) {
                            // Equipment is present, delete the existing document(s)
                            for (document in documents) {
                                collectionRef.document(document.id)
                                    .delete()
                                    .addOnSuccessListener {
                                        updateQuantityInFirebase(equipment, 1){
                                        Toast.makeText(
                                            requireContext(),
                                            "EQUIPMENT RETURNED SUCCESSFULLY",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                            editRoll.text.clear()
                                    }}
                                    .addOnFailureListener { e ->
                                        Toast.makeText(
                                            requireContext(),
                                            "FAILED TO RETURN EQUIPMENT ",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        editRoll.text.clear()
                                    }
                            }
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "FAILED TO RETURN EQUIPMENT 2",
                                Toast.LENGTH_SHORT
                            ).show()
                            editRoll.text.clear()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to fetch data. Please try again.",
                            Toast.LENGTH_SHORT
                        ).show()
                        editRoll.text.clear()
                    }
                }
        } else {
            Toast.makeText(requireContext(), "Enter valid roll number and equipment", Toast.LENGTH_SHORT).show()
        }
    }


    private fun issue() {
        //issue only if the equipment is free

        val roll = editRoll.text.toString()
        val equipment =
            selectedOption.toString()                // if (roll == rollCheck(on first activity) {
        if (roll != "") {

// Check if the roll no. and equipment both is present in Firebase


            collectionRef.whereEqualTo(Roll, roll)
                .whereEqualTo(Equipment, equipment)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {

                        // Roll no. and equipment both  is not present, add a new note
                        val note = mutableMapOf<String, Any>()

                        note[Roll] = roll
                        note[Equipment] = equipment


                        fetchQuantityFromFirebase(equipment) { quantityResult ->
                            if (quantityResult == 3) {
                                updateQuantityInFirebase(equipment, -1){

                                collectionRef.add(note)
                                    .addOnSuccessListener {

                                        Toast.makeText(
                                            requireContext(),
                                            "EQUIPMENT ISSUED SUCCESSFULLY",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        editRoll.text.clear()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(
                                            requireContext(),
                                            "FAILED TO ISSUE EQUIPMENT ",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        editRoll.text.clear()
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "FAILED TO ISSUE EQUIPMENT ",
                                    Toast.LENGTH_SHORT
                                ).show()
                                editRoll.text.clear()
                            }

                        }

                    }
                         else {
                            Toast.makeText(
                                requireContext(),
                                "FAILED TO ISSUE EQUIPMENT ",
                                Toast.LENGTH_SHORT
                            ).show()
                        editRoll.text.clear()
                        }
                    }
                        .addOnFailureListener { e ->
                            Toast.makeText(
                                requireContext(),
                                "SERVER ERROR ,TRY LATER",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            editRoll.text.clear()
                        }


                } else {
                Toast.makeText(requireContext(), "ENTER YOUR ROLL NO.", Toast.LENGTH_SHORT).show()
            }
        }


        private fun fetchQuantityFromFirebase(equipment: String, callback: (Int) -> Unit): Int {
            val equipmentQuantity = db.collection("quantity").document(equipment)
var a=7
          equipmentQuantity.get()
                .addOnSuccessListener { documentSnapshot ->
                    var quantity = documentSnapshot.getLong("number")?.toInt() ?: 0
                    // Compare the quantity and return the appropriate value
                    quantity=quantity.toString().toInt()
                    a = if (quantity >= 1) {
                        3
                    } else {
                        7
                    }
                    callback(a)
                }
                .addOnFailureListener { e ->
                    Log.w("Firebase", "Error fetching quantity", e)
                callback(7)// Handle failure by providing a default value

                }
        return a
        }



    private fun updateQuantityInFirebase(equipment: String, i: Long, callback: () -> Unit) {


        val equipmentRef =db.collection("quantity").document(equipment)




        // Update the quantity by adding or subtracting the quantityChange value
        equipmentRef.update("number", FieldValue.increment(i))

            .addOnSuccessListener {
                
                Log.d("Firebase", "Quantity updated successfully")
                callback()
            }
            .addOnFailureListener { e ->
                Log.w("Firebase", "Error updating quantity", e)
            }


    }
}











