package com.example.ptpshelpcentar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoFragment : Fragment() {


    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_info, container, false)

        val info = view.findViewById<Button>(R.id.infoBTN)
        val simptoms = view.findViewById<Button>(R.id.SimptomsBTN)
        val detalji = view.findViewById<Button>(R.id.DetaljiBTN)

        info.setOnClickListener {
            val intent = Intent(context, InfoDetails::class.java)
            intent.putExtra("buttonID", "Info")
            startActivity(intent)
        }

        simptoms.setOnClickListener {
            val intent = Intent(context, InfoDetails::class.java)
            intent.putExtra("buttonID", "Simptoms")
            startActivity(intent)
        }

        detalji.setOnClickListener {
            val intent = Intent(context, InfoDetails::class.java)
            intent.putExtra("buttonID", "Detalji")
            startActivity(intent)
        }




        return view
    }



}