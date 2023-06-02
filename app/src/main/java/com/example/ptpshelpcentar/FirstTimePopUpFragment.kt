package com.example.ptpshelpcentar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI


class FirstTimePopUpFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_time_pop_up, container, false)

        val btn = view.findViewById<Button>(R.id.NextBTN)


        getDialog()?.getWindow()?.setBackgroundDrawableResource(R.drawable.popup)
        btn.setOnClickListener {
            findNavController().navigate(R.id.testFragment)
            dismiss()
        }


        return view
    }






}