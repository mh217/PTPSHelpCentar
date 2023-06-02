package com.example.ptpshelpcentar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class TestFragment : Fragment() {

    private val db = Firebase.firestore
    private val user = Firebase.auth.currentUser
    var diagnosis: String = ""
    var overallAuth: ArrayList<Boolean> = arrayListOf()
    var BclusterAuth: ArrayList<Boolean> = arrayListOf()
    var CclusterAuth: ArrayList<Boolean> = arrayListOf()
    var DclusterAuth: ArrayList<Boolean> = arrayListOf()
    var EclusterAuth: ArrayList<Boolean> = arrayListOf()
    var overalls: ArrayList<Double> = arrayListOf()
    var Bclusters: ArrayList<Double> = arrayListOf()
    var Cclusters: ArrayList<Double> = arrayListOf()
    var Dclusters: ArrayList<Double> = arrayListOf()
    var Eclusters: ArrayList<Double> = arrayListOf()
    var dateList: ArrayList<String> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_test, container, false)




         user?.let {
             db.collection("Users")
                 .document(user.uid).collection("Details")
                 .document("UserScores2").get()
                 .addOnSuccessListener { snap ->
                     if(snap.data != null) {
                         overalls = snap.data!!["Overall"] as? ArrayList<Double> ?: ArrayList()
                         Bclusters = snap.data!!["Bcluster"] as? ArrayList<Double> ?: ArrayList()
                         Cclusters = snap.data!!["Ccluster"] as? ArrayList<Double> ?: ArrayList()
                         Dclusters = snap.data!!["Dcluster"] as? ArrayList<Double> ?: ArrayList()
                         Eclusters = snap.data!!["Ecluster"] as? ArrayList<Double> ?: ArrayList()
                         dateList = snap.data!!["Date"] as? ArrayList<String>  ?: ArrayList()
                     } else {
                         val dataClusterss = hashMapOf(
                             "Overall" to overalls,
                             "Bcluster" to Bclusters,
                             "Ccluster" to Cclusters,
                             "Dcluster" to Dclusters,
                             "Ecluster" to Eclusters,
                             "Date" to dateList,
                         )

                         user?.let {
                             db.collection("Users")
                                 .document(it.uid).collection("Details")
                                 .document("UserScores2")
                                    .set(dataClusterss)
                            }
                        }

                    }
            }

        user?.let {
            db.collection("Users")
                .document(user.uid).collection("Details")
                .document("AuthofScores").get()
                .addOnSuccessListener { snap ->
                    if (snap.data != null) {
                        overallAuth = snap.data!!["Overall"] as? ArrayList<Boolean> ?: ArrayList()
                        BclusterAuth = snap.data!!["Bcluster"] as? ArrayList<Boolean> ?: ArrayList()
                        CclusterAuth = snap.data!!["Ccluster"] as? ArrayList<Boolean> ?: ArrayList()
                        DclusterAuth = snap.data!!["Dcluster"] as? ArrayList<Boolean> ?: ArrayList()
                        EclusterAuth = snap.data!!["Ecluster"] as? ArrayList<Boolean> ?: ArrayList()
                    } else {
                        val dataClusters = hashMapOf(
                            "Overall" to overallAuth,
                            "Bcluster" to BclusterAuth,
                            "Ccluster" to CclusterAuth,
                            "Dcluster" to DclusterAuth,
                            "Ecluster" to EclusterAuth
                        )

                        user?.let {
                            db.collection("Users")
                                .document(it.uid).collection("Details")
                                .document("AuthofScores")
                                .set(dataClusters)
                        }

                    }

                }
        }

        val radioGroup1 = view.findViewById<RadioGroup>(R.id.RadioPitanje1)
        val radioGroup2 = view.findViewById<RadioGroup>(R.id.RadioPitanje2)
        val radioGroup3 = view.findViewById<RadioGroup>(R.id.RadioPitanje3)
        val radioGroup4 = view.findViewById<RadioGroup>(R.id.RadioPitanje4)
        val radioGroup5 = view.findViewById<RadioGroup>(R.id.RadioPitanje5)
        val radioGroup6 = view.findViewById<RadioGroup>(R.id.RadioPitanje6)
        val radioGroup7 = view.findViewById<RadioGroup>(R.id.RadioPitanje7)
        val radioGroup8 = view.findViewById<RadioGroup>(R.id.RadioPitanje8)
        val radioGroup9 = view.findViewById<RadioGroup>(R.id.RadioPitanje9)
        val radioGroup10 = view.findViewById<RadioGroup>(R.id.RadioPitanje10)
        val radioGroup11 = view.findViewById<RadioGroup>(R.id.RadioPitanje11)
        val radioGroup12 = view.findViewById<RadioGroup>(R.id.RadioPitanje12)
        val radioGroup13 = view.findViewById<RadioGroup>(R.id.RadioPitanje13)
        val radioGroup14 = view.findViewById<RadioGroup>(R.id.RadioPitanje14)
        val radioGroup15 = view.findViewById<RadioGroup>(R.id.RadioPitanje15)
        val radioGroup16 = view.findViewById<RadioGroup>(R.id.RadioPitanje16)
        val radioGroup17 = view.findViewById<RadioGroup>(R.id.RadioPitanje17)
        val radioGroup18 = view.findViewById<RadioGroup>(R.id.RadioPitanje18)
        val radioGroup19 = view.findViewById<RadioGroup>(R.id.RadioPitanje19)
        val radioGroup20 = view.findViewById<RadioGroup>(R.id.RadioPitanje20)
        val button = view.findViewById<Button>(R.id.doneBTN)
        var sum : Double = 0.0
        var reexperiencing : Double = 0.0
        var avoidance : Double = 0.0
        var alterations : Double = 0.0
        var hyperarousal : Double = 0.0


        button.setOnClickListener {
            val selectedOption1: Int = radioGroup1!!.checkedRadioButtonId
            val selectedOption2: Int = radioGroup2!!.checkedRadioButtonId
            val selectedOption3: Int = radioGroup3!!.checkedRadioButtonId
            val selectedOption4: Int = radioGroup4!!.checkedRadioButtonId
            val selectedOption5: Int = radioGroup5!!.checkedRadioButtonId
            val selectedOption6: Int = radioGroup6!!.checkedRadioButtonId
            val selectedOption7: Int = radioGroup7!!.checkedRadioButtonId
            val selectedOption8: Int = radioGroup8!!.checkedRadioButtonId
            val selectedOption9: Int = radioGroup9!!.checkedRadioButtonId
            val selectedOption10: Int = radioGroup10!!.checkedRadioButtonId
            val selectedOption11: Int = radioGroup11!!.checkedRadioButtonId
            val selectedOption12: Int = radioGroup12!!.checkedRadioButtonId
            val selectedOption13: Int = radioGroup13!!.checkedRadioButtonId
            val selectedOption14: Int = radioGroup14!!.checkedRadioButtonId
            val selectedOption15: Int = radioGroup15!!.checkedRadioButtonId
            val selectedOption16: Int = radioGroup16!!.checkedRadioButtonId
            val selectedOption17: Int = radioGroup17!!.checkedRadioButtonId
            val selectedOption18: Int = radioGroup18!!.checkedRadioButtonId
            val selectedOption19: Int = radioGroup19!!.checkedRadioButtonId
            val selectedOption20: Int = radioGroup20!!.checkedRadioButtonId

            if (selectedOption1 <=0  || selectedOption2 <= 0 ||  selectedOption3 <= 0 || selectedOption4 <= 0 || selectedOption5 <= 0 || selectedOption6 <= 0 || selectedOption7 <= 0 || selectedOption8 <= 0 ||
                selectedOption9<= 0 || selectedOption10<= 0 || selectedOption11 <= 0 || selectedOption12<= 0 || selectedOption13<= 0 || selectedOption14<= 0 || selectedOption15<= 0 ||  selectedOption16<= 0 ||
                selectedOption17<= 0 || selectedOption18<= 0 || selectedOption19<= 0 || selectedOption20<= 0) {

                Toast.makeText(context, "Pogreška! Molimo ponovite test.", Toast.LENGTH_SHORT).show()

                radioGroup1.clearCheck()
                radioGroup2.clearCheck()
                radioGroup3.clearCheck()
                radioGroup4.clearCheck()
                radioGroup5.clearCheck()
                radioGroup6.clearCheck()
                radioGroup7.clearCheck()
                radioGroup8.clearCheck()
                radioGroup9.clearCheck()
                radioGroup10.clearCheck()
                radioGroup11.clearCheck()
                radioGroup12.clearCheck()
                radioGroup13.clearCheck()
                radioGroup14.clearCheck()
                radioGroup15.clearCheck()
                radioGroup16.clearCheck()
                radioGroup17.clearCheck()
                radioGroup18.clearCheck()
                radioGroup19.clearCheck()
                radioGroup20.clearCheck()

            }
            else {

                val radioButton1 = view.findViewById<RadioButton>(selectedOption1).text.toString()
                val radioButton2 = view.findViewById<RadioButton>(selectedOption2).text.toString()
                val radioButton3 = view.findViewById<RadioButton>(selectedOption3).text.toString()
                val radioButton4 = view.findViewById<RadioButton>(selectedOption4).text.toString()
                val radioButton5 = view.findViewById<RadioButton>(selectedOption5).text.toString()
                val radioButton6 = view.findViewById<RadioButton>(selectedOption6).text.toString()
                val radioButton7 = view.findViewById<RadioButton>(selectedOption7).text.toString()
                val radioButton8 = view.findViewById<RadioButton>(selectedOption8).text.toString()
                val radioButton9 = view.findViewById<RadioButton>(selectedOption9).text.toString()
                val radioButton10 = view.findViewById<RadioButton>(selectedOption10).text.toString()
                val radioButton11 = view.findViewById<RadioButton>(selectedOption11).text.toString()
                val radioButton12 = view.findViewById<RadioButton>(selectedOption12).text.toString()
                val radioButton13 = view.findViewById<RadioButton>(selectedOption13).text.toString()
                val radioButton14 = view.findViewById<RadioButton>(selectedOption14).text.toString()
                val radioButton15 = view.findViewById<RadioButton>(selectedOption15).text.toString()
                val radioButton16 = view.findViewById<RadioButton>(selectedOption16).text.toString()
                val radioButton17 = view.findViewById<RadioButton>(selectedOption17).text.toString()
                val radioButton18 = view.findViewById<RadioButton>(selectedOption18).text.toString()
                val radioButton19 = view.findViewById<RadioButton>(selectedOption19).text.toString()
                val radioButton20 = view.findViewById<RadioButton>(selectedOption20).text.toString()

                val number1: Double = radioButton1.toDouble()
                val number2: Double = radioButton2.toDouble()
                val number3: Double = radioButton3.toDouble()
                val number4: Double = radioButton4.toDouble()
                val number5: Double = radioButton5.toDouble()
                val number6: Double = radioButton6.toDouble()
                val number7: Double = radioButton7.toDouble()
                val number8: Double = radioButton8.toDouble()
                val number9: Double = radioButton9.toDouble()
                val number10: Double = radioButton10.toDouble()
                val number11: Double = radioButton11.toDouble()
                val number12: Double = radioButton12.toDouble()
                val number13: Double = radioButton13.toDouble()
                val number14: Double = radioButton14.toDouble()
                val number15: Double = radioButton15.toDouble()
                val number16: Double = radioButton16.toDouble()
                val number17: Double = radioButton17.toDouble()
                val number18: Double = radioButton18.toDouble()
                val number19: Double = radioButton19.toDouble()
                val number20: Double = radioButton20.toDouble()

                var allarray: ArrayList<Double> = arrayListOf()
                allarray.addAll(
                    listOf(
                        number1,
                        number2,
                        number3,
                        number4,
                        number5,
                        number6,
                        number7,
                        number8,
                        number9,
                        number10,
                        number11,
                        number12,
                        number13,
                        number14,
                        number15,
                        number16,
                        number17,
                        number18,
                        number19,
                        number20
                    )
                )
                var reexper: ArrayList<Double> = arrayListOf()
                reexper.addAll(listOf(number1, number2, number3, number4, number5))
                var avoid: ArrayList<Double> = arrayListOf()
                avoid.addAll(listOf(number6, number7))
                var alter: ArrayList<Double> = arrayListOf()
                alter.addAll(
                    listOf(
                        number8,
                        number9,
                        number10,
                        number11,
                        number12,
                        number13,
                        number14
                    )
                )
                var hyper: ArrayList<Double> = arrayListOf()
                hyper.addAll(listOf(number15, number16, number17, number18, number19, number20))

                scoring(allarray)
                scoringSymp(reexper, avoid, alter, hyper)

                val dataClusters = hashMapOf(
                    "Overall" to overallAuth,
                    "Bcluster" to BclusterAuth,
                    "Ccluster" to CclusterAuth,
                    "Dcluster" to DclusterAuth,
                    "Ecluster" to EclusterAuth
                )

                user?.let {
                    db.collection("Users")
                        .document(it.uid).collection("Details")
                        .document("AuthofScores")
                        .set(dataClusters)
                }

                Toast.makeText(context, "Spremljena je procjena", Toast.LENGTH_SHORT).show()


                reexperiencing = number1 + number2 + number3 + number4 + number5
                avoidance = number6 + number7
                alterations =
                    number8 + number9 + number10 + number11 + number12 + number13 + number14
                hyperarousal = number15 + number16 + number17 + number18 + number19 + number20
                sum = reexperiencing + avoidance + alterations + hyperarousal
                val formatter = SimpleDateFormat("dd-MM")
                val date = Date()
                val current = formatter.format(date).toString()
                diagnosis(sum)



                overalls.add(sum)
                Bclusters.add(reexperiencing)
                Cclusters.add(avoidance)
                Dclusters.add(alterations)
                Eclusters.add(hyperarousal)
                dateList.add(current)

                val dataClusterss = hashMapOf(
                    "Overall" to overalls,
                    "Bcluster" to Bclusters,
                    "Ccluster" to Cclusters,
                    "Dcluster" to Dclusters,
                    "Ecluster" to Eclusters,
                    "Date" to dateList,
                )

                user?.let {
                    db.collection("Users")
                        .document(it.uid).collection("Details")
                        .document("UserScores2")
                        .set(dataClusterss)
                }

                Toast.makeText(context, "Vaši rezultati su spremljeni!", Toast.LENGTH_SHORT).show()

                radioGroup1.clearCheck()
                radioGroup2.clearCheck()
                radioGroup3.clearCheck()
                radioGroup4.clearCheck()
                radioGroup5.clearCheck()
                radioGroup6.clearCheck()
                radioGroup7.clearCheck()
                radioGroup8.clearCheck()
                radioGroup9.clearCheck()
                radioGroup10.clearCheck()
                radioGroup11.clearCheck()
                radioGroup12.clearCheck()
                radioGroup13.clearCheck()
                radioGroup14.clearCheck()
                radioGroup15.clearCheck()
                radioGroup16.clearCheck()
                radioGroup17.clearCheck()
                radioGroup18.clearCheck()
                radioGroup19.clearCheck()
                radioGroup20.clearCheck()

            }

        }
        return view
    }

    fun diagnosis(sum: Double) {
        if(sum <= 31.0) {
           diagnosis = "LOW"
        }
        if(sum in 32.0..51.0) {
            diagnosis = "MODERATE"
        }
        if (sum in 52.0..80.0) {
           diagnosis = "HIGH"
        }

    }

    fun scoring(scores : ArrayList<Double>) {
        var br :Int = 0
        for (score in scores) {
            if (score >= 2) {
                br += 1
            }
        }
        if (br == 20) {
            overallAuth.add(true)
        }
        else{
            overallAuth.add(false)
        }

    }

    fun scoringSymp(cluster1: ArrayList<Double>,cluster2: ArrayList<Double>,cluster3: ArrayList<Double>,cluster4: ArrayList<Double>) {
        var brB :Int = 0
        var brC :Int = 0
        var brD :Int = 0
        var brE :Int = 0
        for (cluster in cluster1) {
            if (cluster >= 2) {
                brB +=1
            }
        }
        if (brB >= 2) {
            BclusterAuth.add(true)
        }
        else{
            BclusterAuth.add(false)
        }


        for (cluster in cluster2) {
            if(cluster >= 2) {
                brC +=1
            }
        }
        if (brC>=2) {
            CclusterAuth.add(true)
        }
        else {
            CclusterAuth.add(false)
        }

        for (cluster in cluster3) {
            if (cluster >=2) {
                brD += 1
            }
        }
        if (brD>=2) {
            DclusterAuth.add(true)
        }
        else {
            DclusterAuth.add(false)
        }
        for (cluster in cluster4) {
            if (cluster >=2) {
                brE += 1
            }
        }
        if (brE >=2) {
            EclusterAuth.add(true)
        }
        else {
            EclusterAuth.add(false)
        }

    }

}