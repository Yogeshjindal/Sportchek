package com.example.anavnaigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

class Adapter2(
    private val context: Context,
    private val equipmentRollList: List<EquipmentRollPair>


) : RecyclerView.Adapter<Adapter2.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.issued_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.bind(equipmentRollList[position])

      

        }



    override fun getItemCount(): Int {
        return equipmentRollList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val equipmentList = itemView.findViewById<TextView>(R.id.name2)!!
        val rollList = itemView.findViewById<TextView>(R.id.issuedBy)!!
        val number = itemView.findViewById<TextView>(R.id.serial)!!
        fun bind(item: EquipmentRollPair) {
            // Bind equipment and roll data to the ViewHolder
            // You can access item.equipment and item.roll here
           equipmentList.text = "Issued- ${item.equipment.toString()}"
          rollList.text = "Issued By- ${item.roll.toString()}"

        }



    }
}
