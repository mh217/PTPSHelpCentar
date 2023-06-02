package com.example.ptpshelpcentar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.findFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val logout = view.findViewById<ImageButton>(R.id.logoutButton)
        val name = view.findViewById<TextView>(R.id.NameTxt)
        val surname = view.findViewById<TextView>(R.id.SurnameTxt)
        val date = view.findViewById<TextView>(R.id.DateofBirthTxt)
        val city = view.findViewById<TextView>(R.id.CityTxt)
        val meds = view.findViewById<TextView>(R.id.MedsTxt)
        val email = view.findViewById<TextView>(R.id.EmailTxt)
        val gender = view.findViewById<TextView>(R.id.SpolTxt)



        user?.let {
            val mail = it.email
            email.text = mail
        }

        user?.let{
            db.collection("Users")
                .document(it.uid).collection("Details")
                .document("UserDetails").get()
                .addOnSuccessListener { it->
                    name.text = it.data!!["Name"].toString()
                    surname.text = it.data!!["Surname"].toString()
                    date.text = it.data!!["DateofBirth"].toString()
                    city.text = it.data!!["City"].toString()
                    meds.text = it.data!!["NameofMeds"].toString()
                    gender.text = it.data!!["Gender"].toString()
                }
        }



        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, Login::class.java)
            startActivity(intent)
            val activity = context as Activity?
            activity!!.finish()
        }

        return view
    }
}