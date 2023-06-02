package com.example.ptpshelpcentar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ptpshelpcentar.databinding.ActivityInfoDetailsBinding
import com.example.ptpshelpcentar.databinding.ActivityTreatmentDetailsBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InfoDetails : AppCompatActivity() {

    private lateinit var binding: ActivityInfoDetailsBinding
    private var buttonid =""
    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityInfoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonid = intent.getStringExtra("buttonID")!!
        val button = binding.backbutton

        if (buttonid == "Info") {
            user?.let {
                db.collection("Info")
                    .document("PTSP")
                    .get()
                    .addOnSuccessListener { it ->
                        val head = it.get("Name")
                        val description = it.get("Description")
                        val image = it.get("Image")
                        val description2 = it.get("Description2")
                        val image2 = it.get("Image2")

                        Glide
                            .with(this)
                            .load(image)
                            .into(binding.imageInfo)

                        Glide
                            .with(this)
                            .load(image2)
                            .into(binding.imageInfo2)

                        binding.heading.text = head.toString()
                        binding.DescriotionInfo.text = description.toString()
                        binding.DescriotionInfo2.text = description2.toString()

                    }
            }

        }
        else if (buttonid == "Simptoms") {
            user?.let {
                db.collection("Info")
                    .document("Simptomi")
                    .get()
                    .addOnSuccessListener { it ->
                        val head = it.get("Name")
                        val description = it.get("Description")
                        val image = it.get("Image")
                        val description2 = it.get("Description2")
                        val image2 = it.get("Image2")

                        Glide
                            .with(this)
                            .load(image)
                            .into(binding.imageInfo)

                        Glide
                            .with(this)
                            .load(image2)
                            .into(binding.imageInfo2)

                        binding.heading.text = head.toString()
                        binding.DescriotionInfo.text = description.toString()
                        binding.DescriotionInfo2.text = description2.toString()
                    }
            }
        }
        else{
            user?.let {
                db.collection("Info")
                    .document("Detalji")
                    .get()
                    .addOnSuccessListener { it ->
                        val head = it.get("Name")
                        val description = it.get("Description")
                        val image = it.get("Image")
                        val description2 = it.get("Description2")
                        val image2 = it.get("Image2")

                        Glide
                            .with(this)
                            .load(image)
                            .into(binding.imageInfo)

                        Glide
                            .with(this)
                            .load(image2)
                            .into(binding.imageInfo2)

                        binding.heading.text = head.toString()
                        binding.DescriotionInfo.text = description.toString()
                        binding.DescriotionInfo2.text = description2.toString()
                    }
            }
        }

        button?.setOnClickListener {
            finish()
        }
    }
}