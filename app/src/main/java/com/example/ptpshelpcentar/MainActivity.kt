package com.example.ptpshelpcentar


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView.SavedState
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.ptpshelpcentar.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        //val newUser: String? = intent.getStringExtra("registration")

        super.onPostResume()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)


        /* if(newUser == "true"){
            val bundle = intent.extras
            navHostFragment.navController.navigate(R.id.testFragment, bundle)
        }

         */


    }




}