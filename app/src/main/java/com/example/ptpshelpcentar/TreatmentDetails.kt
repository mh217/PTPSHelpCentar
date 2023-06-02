package com.example.ptpshelpcentar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ptpshelpcentar.databinding.ActivityTreatmentDetailsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TreatmentDetails : AppCompatActivity() {

    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore
    private lateinit var binding: ActivityTreatmentDetailsBinding
    private var treatmentId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityTreatmentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.NameTxttreatment
        val description = binding.DescriptionTxt
        val image = binding.image1
        val btn = binding.BackBTNN
        val description1 = binding.DescriptionTxt2
        val image2 = binding.image2

        treatmentId = intent.getStringExtra("treatmentID")!!

        user?.let {
            db.collection("Treatment")
                .document(treatmentId)
                .get()
                .addOnSuccessListener { result ->
                    val name = result.get("Name")
                    val description = result.get("Description")
                    val image = result.get("Image1")
                    val description2 = result.get("Description1")
                    val image2 = result.get("Image2")

                    Glide
                        .with(this)
                        .load(image)
                        .into(binding.image1)

                    Glide
                        .with(this)
                        .load(image2)
                        .into(binding.image2)

                    binding.NameTxttreatment.text = name.toString()
                    binding.DescriptionTxt.text = description.toString()
                    binding.DescriptionTxt2.text = description2.toString()


                        }
                    }

        btn?.setOnClickListener {
            finish()
        }
                }
        }


