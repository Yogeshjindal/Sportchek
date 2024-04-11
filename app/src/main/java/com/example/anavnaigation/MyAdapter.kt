package com.example.anavnaigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyAdapter(

    private val context: Context,
    private val equipment: List<String>,
    private val photos: List<Int>,
    private val numbers: List<String>,
    private val quantity: List<String>,
    private val navController: NavController

) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    companion object {
        const val EQUIPMENT_ITEM_KEY1 = "equipmentItem"
        const val EQUIPMENT_ITEM_KEY2 = "quantityItem"
        const val EQUIPMENT_ITEM_KEY3 = "photoItem"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.name.text = equipment[position]
        holder.serial.text = numbers[position]
        holder.quantity.text = quantity[position]
        holder.imageView.setImageResource(photos[position])

        val equipmentItem = equipment[position]

        val quantityItem = quantity[position]
        val photoItem = photos[position]



        holder.itemView.setOnClickListener {
            // Navigate to the EquipmentDetailFragment

            val bundle = Bundle().apply {
                putString(MyAdapter.EQUIPMENT_ITEM_KEY1, equipmentItem)
                putString(MyAdapter.EQUIPMENT_ITEM_KEY2, quantityItem)
                putString(MyAdapter.EQUIPMENT_ITEM_KEY3, photoItem.toString())

            }

            navController.navigate(R.id.equipmentDetail,bundle)
        }





    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)!!
        val serial = itemView.findViewById<TextView>(R.id.serial)!!
        val quantity = itemView.findViewById<TextView>(R.id.quantity)!!
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

}






