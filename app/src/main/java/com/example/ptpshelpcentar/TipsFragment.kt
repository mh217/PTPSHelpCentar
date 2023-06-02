package com.example.ptpshelpcentar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ptpshelpcentar.data.Treatment
import com.example.ptpshelpcentar.recyclers.TreatmentAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TipsFragment : Fragment() {

    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore
    private lateinit var treatmentAdapter: TreatmentAdapter
    var overalls : ArrayList<Double> = arrayListOf()
    var Bclusters : ArrayList<Double> = arrayListOf()
    var Cclusters : ArrayList<Double> = arrayListOf()
    var Dclusters : ArrayList<Double> = arrayListOf()
    var Eclusters : ArrayList<Double> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tips, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.RecyclerViewTreatment)


        user?.let {
            db.collection("Users")
                .document(user.uid).collection("Details")
                .document("UserScores2").get()
                .addOnSuccessListener { snap ->
                    if (snap.data != null) {
                        overalls = snap.data!!["Overall"] as? ArrayList<Double> ?: ArrayList()
                        Bclusters = snap.data!!["Bcluster"] as? ArrayList<Double> ?: ArrayList()
                        Cclusters = snap.data!!["Ccluster"] as? ArrayList<Double> ?: ArrayList()
                        Dclusters = snap.data!!["Dcluster"] as? ArrayList<Double> ?: ArrayList()
                        Eclusters = snap.data!!["Ecluster"] as? ArrayList<Double> ?: ArrayList()
                    }
                    else {
                        val dataClusterss = hashMapOf(
                            "Overall" to overalls,
                            "Bcluster" to Bclusters,
                            "Ccluster" to Cclusters,
                            "Dcluster" to Dclusters,
                            "Ecluster" to Eclusters,
                        )

                        user?.let {
                            db.collection("Users")
                                .document(it.uid).collection("Details")
                                .document("UserScores2")
                                .set(dataClusterss)
                        }
                    }

                }
                .addOnCompleteListener {
                    if(overalls.isNotEmpty() && Bclusters.isNotEmpty() && Cclusters.isNotEmpty() && Dclusters.isNotEmpty() && Eclusters.isNotEmpty()) {
                        var diagnosis: String = Scorer.scoring(
                            overalls.last(),
                            Bclusters.last(),
                            Cclusters.last(),
                            Dclusters.last(),
                            Eclusters.last(),
                            1
                        )

                        when (diagnosis) {
                            "LOW" -> {
                                db.collection("Treatment")
                                    .whereArrayContains("Risk", "LOW")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        val treatmentArrayList: ArrayList<Treatment> = ArrayList()
                                        for (data in result.documents) {
                                            val treatments = data.toObject(Treatment::class.java)
                                            if (treatments != null) {
                                                treatments.Id = data.id
                                                treatmentArrayList.add(treatments)
                                            }
                                        }
                                        treatmentAdapter = TreatmentAdapter(treatmentArrayList)
                                        recycler.apply {
                                            layoutManager = LinearLayoutManager(context)
                                            adapter = treatmentAdapter
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Scores", "Error getting documents", exception)

                                    }
                            }
                            "MEDIUM" -> {
                                db.collection("Treatment")
                                    .whereArrayContains("Risk", "MEDIUM")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        val treatmentArrayList: ArrayList<Treatment> = ArrayList()
                                        for (data in result.documents) {
                                            val treatments = data.toObject(Treatment::class.java)
                                            if (treatments != null) {
                                                treatments.Id = data.id
                                                treatmentArrayList.add(treatments)
                                            }
                                        }
                                        treatmentAdapter = TreatmentAdapter(treatmentArrayList)
                                        recycler.apply {
                                            layoutManager = LinearLayoutManager(context)
                                            adapter = treatmentAdapter
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Scores", "Error getting documents", exception)

                                    }
                            }
                            "HIGH" -> {
                                db.collection("Treatment")
                                    .whereArrayContains("Risk", "HIGH")
                                    .get()
                                    .addOnSuccessListener { result ->
                                        val treatmentArrayList: ArrayList<Treatment> = ArrayList()
                                        for (data in result.documents) {
                                            val treatments = data.toObject(Treatment::class.java)
                                            if (treatments != null) {
                                                treatments.Id = data.id
                                                treatmentArrayList.add(treatments)
                                            }
                                        }
                                        treatmentAdapter = TreatmentAdapter(treatmentArrayList)
                                        recycler.apply {
                                            layoutManager = LinearLayoutManager(context)
                                            adapter = treatmentAdapter
                                        }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.w("Scores", "Error getting documents", exception)

                                    }

                            }
                        }
                    }
                    else {
                        db.collection("Treatment")
                            .get()
                            .addOnSuccessListener { result ->
                                val treatmentArrayList: ArrayList<Treatment> = ArrayList()
                                for (data in result.documents) {
                                    val treatments = data.toObject(Treatment::class.java)
                                    if (treatments != null) {
                                        treatments.Id = data.id
                                        treatmentArrayList.add(treatments)
                                    }
                                }
                                treatmentAdapter = TreatmentAdapter(treatmentArrayList)
                                recycler.apply {
                                    layoutManager = LinearLayoutManager(context)
                                    adapter = treatmentAdapter
                                }
                            }
                            .addOnFailureListener { exception ->
                                Log.w("Scores", "Error getting documents", exception)

                            }
                        Toast.makeText(context, "Za savjete potrebno riješiti test", Toast.LENGTH_SHORT).show()
                    }

                }
        }


 return view
}


}