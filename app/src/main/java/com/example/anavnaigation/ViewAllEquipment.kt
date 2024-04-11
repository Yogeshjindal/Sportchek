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
interface QuantityCallback {
    fun onSuccess(quantityValue: Int?)
    fun onSuccess2(quantityValue: EquipmentRollPair, rollValue:String?)
    fun onFailure()
    fun onSuccessEquipmentRollPair(equipmentRollPair: EquipmentRollPair) {


    }
}
class ViewAllEquipment : Fragment() {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val docRef: DocumentReference = db.collection("user").document()
    private val collectionRef: CollectionReference = db.collection("user")
    private val QuantityDocRef: DocumentReference = db.collection("quantity").document()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_view_all_equipment, container, false)

        val equipmentList= view?.findViewById<RecyclerView>(R.id.equipmentList)

        val equipment: MutableList<String> = mutableListOf("Badminton Racket","Basket Ball","Carromboard","Chessboard",
            "Cricket Ball","Cricket Bat","Football","Table Tennis Racket","Tennis Racket","Volleyball")
        val numbers:List<String> = listOf("1:","2:","3:","4:","5:","6:","7:","8:","9:","10:")

        val quantity: MutableList<String> = mutableListOf("Quantity: 4","Quantity: 2","Quantity: 3","Quantity: 4","Quantity: 2",
            "Quantity: 3","Quantity: 1","Quantity: 4","Quantity: 2","Quantity: 2")
        val photos: List<Int> = listOf(
            R.drawable.badmintonracket,
            R.drawable.basketball,
            R.drawable.carromboard,
            R.drawable.chessboard,
            R.drawable.cricketball,
            R.drawable.cricketbat,
            R.drawable.football,
            R.drawable.tabletennisracket,
            R.drawable.tennisracket,
            R.drawable.volleyball

        )


        for (i in equipment.indices) {
            fetchQuantityFromFirebase(equipment[i], object : QuantityCallback {
                override fun onSuccess(quantityValue: Int?) {
                    // Update the quantity list with the fetched value
                    quantity[i] = "Quantity: $quantityValue"
                    // Notify the adapter that the data has changed
                    equipmentList?.adapter?.notifyDataSetChanged()
                }

                override fun onSuccess2(quantityValue: EquipmentRollPair, rollValue: String?) {

                }

                override fun onFailure() {
                    // Handle failure (optional)
                }
            })
        }

        val navController = findNavController()
        equipmentList?.layoutManager=LinearLayoutManager(context)
        equipmentList?.adapter = context?.let { MyAdapter(it,equipment,photos,numbers,quantity,navController) }




        return view
    }
    private fun fetchQuantityFromFirebase(equipment: String, callback: QuantityCallback) {
        val equipmentRef = db.collection("quantity").document(equipment)

        equipmentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val quantityValue = documentSnapshot.getLong("number")?.toInt()
                    if (quantityValue != null) {
                        // Invoke the callback with the quantity value
                        callback.onSuccess(quantityValue)
                    } else {
                        // Handle the case when the field is null
                        callback.onFailure()
                    }
                } else {
                    // Document doesn't exist
                    callback.onFailure()
                }
            }
            .addOnFailureListener { exception ->
                // Handle errors
                callback.onFailure()
            }
    }

}





