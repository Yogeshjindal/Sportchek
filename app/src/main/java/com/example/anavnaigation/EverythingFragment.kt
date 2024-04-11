package com.example.anavnaigation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation


class EverythingFragment : Fragment(),View.OnClickListener {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view= inflater.inflate(R.layout.fragment_everything, container, false )
//        val backgroundImageView: ImageView = view.findViewById(R.id.backgroundImageView)
//        backgroundImageView.setImageResource(R.drawable.img2)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        view.findViewById<Button>(R.id.View).setOnClickListener(this)
        view.findViewById<Button>(R.id.Issue).setOnClickListener(this)
        view.findViewById<Button>(R.id.ViewIssued).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.View -> navController.navigate(R.id.action_everythingFragment_to_viewAllEquipment)
            R.id.Issue -> navController.navigate(R.id.action_everythingFragment_to_issue)
            R.id.ViewIssued -> navController.navigate(R.id.action_everythingFragment_to_viewIssued)

        }
    }

}


