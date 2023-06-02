package com.example.ptpshelpcentar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class Registration : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    lateinit var btnDataPicker : FloatingActionButton
    var genderBTN : String = ""
    var medsBTN : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()

        val nameText = findViewById<EditText>(R.id.NameEditText)
        val surnameText = findViewById<EditText>(R.id.SurnameEditText)
        btnDataPicker = findViewById(R.id.floatingActionButton)
        val emailText = findViewById<EditText>(R.id.RegistrationEmail)
        val passwordText = findViewById<EditText>(R.id.RegistrationPassword)
        val registrationBTN = findViewById<Button>(R.id.RegistBTN)
        val city = findViewById<EditText>(R.id.City)
        val gender = findViewById<RadioGroup>(R.id.RadioGender)
        val meds = findViewById<RadioGroup>(R.id.RadioMeds)
        val specificMeds = findViewById<EditText>(R.id.SpecificMeds) as TextView


        auth = Firebase.auth
        var dateBirth : String =""

        btnDataPicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormatter.format(Date(it))
                dateBirth = date.toString()
                Toast.makeText(baseContext, "Datum je unesen", Toast.LENGTH_SHORT).show()
            }
        }



        registrationBTN.setOnClickListener {
            if(emailText.text.isNotEmpty() && passwordText.text.isNotEmpty() && nameText.text.isNotEmpty() && surnameText.text.isNotEmpty() && city.text.isNotEmpty() && dateBirth.isNotEmpty()) {
                if(passwordText.text.length>=7) {

                    val selectedOption1 = gender.checkedRadioButtonId
                    genderBTN = when(selectedOption1) {
                        R.id.Female -> "F"
                        R.id.Male -> "M"
                        else -> "Ne želim dijeliti"
                    }
                    val selectedOption2 = meds.checkedRadioButtonId
                    medsBTN = when (selectedOption2) {
                        R.id.Da -> "DA"
                        R.id.Ne -> "NE"
                        else -> "NE"
                    }

                    if(specificMeds.text.isEmpty()) {
                        specificMeds.setText("Osoba ne uzima nikakve lijekove ili ne zna o kojima je riječ")
                    }


                    registration(emailText.text.toString(), passwordText.text.toString(), nameText.text.toString(), surnameText.text.toString(), dateBirth, genderBTN, medsBTN, city.text.toString(), specificMeds.text.toString())
                }
                else {
                    Toast.makeText(baseContext, "Pronađite dužu lozinku (minimalno 7 znakova)", Toast.LENGTH_SHORT).show()
                    passwordText.text.clear()

                }
            }
            else {
                Toast.makeText(baseContext, "Molim da popunite sva polja", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun registration(email: String, password: String, name: String, surname: String, datePicked: String, gender: String, meds: String, city: String, specificmeds: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    Toast.makeText(baseContext, "Racun stvoren", Toast.LENGTH_SHORT).show()


                    val user = auth.currentUser
                    val userData = hashMapOf(
                        "Name" to name,
                        "Surname" to surname,
                        "DateofBirth" to datePicked,
                        "Gender" to gender,
                        "Medicine" to meds,
                        "City" to city,
                        "NameofMeds" to specificmeds

                    )

                    user?.uid?.let {
                        db.collection("Users")
                            .document(it)
                            .collection("Details")
                            .document("UserDetails")
                            .set(userData)
                    }

                val intent = Intent(this, MainActivity::class.java)
                    //intent.putExtra("registration", "true")
                    startActivity(intent)
                Toast.makeText(this,"Uspjesna prijava", Toast.LENGTH_SHORT).show()
                finish()
                }

                else {
                    Toast.makeText(baseContext, "Pogreska", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

