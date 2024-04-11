package com.example.anavnaigation

import android.os.Bundle
import android.text.InputFilter

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class MainFragment : Fragment() ,View.OnClickListener {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private val Rollcheck = "rollcheck"

    private val collectionRef: CollectionReference = db.collection("profile")
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        editEmail = view.findViewById(R.id.editTextEmail)
        editPassword = view.findViewById(R.id.editTextPassword)
        btnLogin = view.findViewById(R.id.login)
        btnLogin.setOnClickListener {
            editEmail.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                // Use a regex to check if the input is a valid email address
                val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
                if (source.toString().matches(regex)) {
                    null // Accept the input

                } else {
                    ""  // Reject the input
                }
            })
            if (isValidEmail()) {
            navigationRequirements()
        }
            else{
                Toast.makeText(requireContext(),"Enter correct credentials",Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }
    private fun isValidEmail(): Boolean {
        val regex = "(\\d+)@gmail\\.iiitu.ac.in".toRegex()
        val matchResult = regex.find(editEmail.text.toString())
        return matchResult != null && matchResult.groupValues.size == 2
    }
    private fun navigationRequirements() {
        Toast.makeText(context,"1",Toast.LENGTH_SHORT).show()
        val regex = "(\\d+)@gmail\\.com".toRegex()
        val matchResult = regex.find(editEmail.text.toString())
            val rollcheck = matchResult?.groupValues?.get(1)
            val note = mutableMapOf<String, Any>()
        val documentId = "user_$rollcheck"
            note[Rollcheck] = rollcheck.toString()
        collectionRef.document(documentId).set(note)
                .addOnSuccessListener {
                    navController.navigate(R.id.action_mainFragment_to_everythingFragment)
                    Toast.makeText(context,"scsc",Toast.LENGTH_SHORT).show()
                }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.login).setOnClickListener(this)


    }

    override fun onClick(view: View?) {

        if (
            (editEmail.text.toString() != "") &&
            (editPassword.text.toString() != "")
        ) {

            val regex = "(\\d+)@iiitu\\.ac.in".toRegex()
            val matchResult = regex.find(editEmail.text.toString())
            val rollcheck = matchResult?.groupValues?.get(1)
            val note = mutableMapOf<String, Any>()
            val documentId = "user_$rollcheck"
            note[Rollcheck] = rollcheck.toString()
            collectionRef.document(documentId).set(note)
                .addOnSuccessListener {
                    navController.navigate(R.id.action_mainFragment_to_everythingFragment)

                }
            when (view?.id) {






            }
            editEmail.text.clear()

            editPassword.text.clear()

        } else {
            Toast.makeText(requireContext(),"FILL THE CREDENTIALS",Toast.LENGTH_SHORT).show()
        }

    }

}






