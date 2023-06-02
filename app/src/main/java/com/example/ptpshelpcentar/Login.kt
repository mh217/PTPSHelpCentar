package com.example.ptpshelpcentar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {



    private lateinit var auth: FirebaseAuth
    private val db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        auth = Firebase.auth

        val emailText = findViewById<EditText>(R.id.EmailEditText)
        val passwordText = findViewById<EditText>(R.id.PasswordEdittext)
        val loginBTN = findViewById<Button>(R.id.LoginButton)
        val registrationBTN = findViewById<Button>(R.id.RegisterButton)

        val user =Firebase.auth.currentUser
        if(user != null) {
          val intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
            finish()
        }
        else
        {
          loginBTN.setOnClickListener {
              if(emailText.text.isNotEmpty() && passwordText.text.isNotEmpty()) {
                  LogIn(emailText.text.toString(), passwordText.text.toString())
              }
              else {
                  Toast.makeText(baseContext, "Molimo unesite email i lozinku", Toast.LENGTH_SHORT).show()
              }
          }

          registrationBTN.setOnClickListener {
              val intent = Intent(this, Registration::class.java)
              startActivity(intent)
              finish()
          }

        }

    }

    fun LogIn(email : String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
                    val emailText = findViewById<EditText>(R.id.EmailEditText)
                    val passwordText = findViewById<EditText>(R.id.PasswordEdittext)
                    emailText.text.clear()
                    passwordText.text.clear()
                }
            }
    }

}