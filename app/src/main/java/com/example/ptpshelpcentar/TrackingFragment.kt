package com.example.ptpshelpcentar

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.ptpshelpcentar.recyclers.ScoresAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TrackingFragment : Fragment() {


    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore
    private lateinit var scoresAdapter : ScoresAdapter
    var overalls : ArrayList<Double> = arrayListOf()
    var Bclusters : ArrayList<Double> = arrayListOf()
    var Cclusters : ArrayList<Double> = arrayListOf()
    var Dclusters : ArrayList<Double> = arrayListOf()
    var Eclusters : ArrayList<Double> = arrayListOf()
    var dateList : ArrayList<String>  = arrayListOf()
    var overallsAuth : ArrayList<Boolean> = arrayListOf()
    var BclusterAuth : ArrayList<Boolean> = arrayListOf()
    var CclusterAuth : ArrayList<Boolean> = arrayListOf()
    var DclusterAuth : ArrayList<Boolean> = arrayListOf()
    var EclusterAuth : ArrayList<Boolean> = arrayListOf()

    lateinit var  barChart: BarChart
    lateinit var barDataSet : BarDataSet
    lateinit var barEntriesList : ArrayList<BarEntry>
    lateinit var barData: BarData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_tracking, container, false)

        barChart = view.findViewById(R.id.barChart)
        val dateTXT = view.findViewById<TextView>(R.id.Datum)
        val bklaster = view.findViewById<TextView>(R.id.Bklaster)
        val cklaster = view.findViewById<TextView>(R.id.Cklaster)
        val dklaster = view.findViewById<TextView>(R.id.Dklaster)
        val eklaster = view.findViewById<TextView>(R.id.Eklaster)
        val ukupno = view.findViewById<TextView>(R.id.Ukupno)
        val dijagnoza = view.findViewById<TextView>(R.id.Dijagnoza)
        val vjerodostojnost = view.findViewById<TextView>(R.id.Validacija)


        user?.let {
            db.collection("Users")
                .document(user.uid).collection("Details")
                .document("AuthofScores").get()
                .addOnSuccessListener { snap ->
                    if (snap.data != null) {
                        overallsAuth = snap.data!!["Overall"] as? ArrayList<Boolean> ?: ArrayList()
                        BclusterAuth = snap.data!!["Bcluster"] as? ArrayList<Boolean> ?: ArrayList()
                        CclusterAuth = snap.data!!["Ccluster"] as? ArrayList<Boolean> ?: ArrayList()
                        DclusterAuth = snap.data!!["Dcluster"] as? ArrayList<Boolean> ?: ArrayList()
                        EclusterAuth = snap.data!!["Ecluster"] as? ArrayList<Boolean> ?: ArrayList()

                    }
                    else {
                        val dataClusters = hashMapOf(
                            "Overall" to overallsAuth,
                            "Bcluster" to BclusterAuth,
                            "Ccluster" to CclusterAuth,
                            "Dcluster" to DclusterAuth,
                            "Ecluster" to EclusterAuth,
                        )

                        user?.let {
                            db.collection("Users")
                                .document(it.uid).collection("Details")
                                .document("AuthofScores")
                                .set(dataClusters)
                        }
                    }

                }
                .addOnCompleteListener {
                    if(overallsAuth.isNotEmpty() && BclusterAuth.isNotEmpty() && CclusterAuth.isNotEmpty() && DclusterAuth.isNotEmpty() && EclusterAuth.isNotEmpty()) {
                        vjerodostojnost.text = Scorer.validation(overallsAuth.last(), BclusterAuth.last(), CclusterAuth.last(), DclusterAuth.last(), EclusterAuth.last())
                    }
                    else {
                        vjerodostojnost.text = "Nepoznato"
                    }
                }
        }



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
                       dateList = snap.data!!["Date"] as? ArrayList<String> ?: ArrayList()
                   }
                   else {
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
               .addOnCompleteListener {
                   if (overalls.isNotEmpty() && Bclusters.isNotEmpty() && Cclusters.isNotEmpty() && Dclusters.isNotEmpty() && Eclusters.isNotEmpty() && dateList.isNotEmpty()) {
                       dateTXT.text = dateList.last().toString()
                       bklaster.text = Bclusters.last().toString()
                       cklaster.text = Cclusters.last().toString()
                       dklaster.text = Dclusters.last().toString()
                       eklaster.text = Eclusters.last().toString()
                       ukupno.text = overalls.last().toString()
                       dijagnoza.text = Scorer.scoring(
                           overalls.last(),
                           Bclusters.last(),
                           Cclusters.last(),
                           Dclusters.last(),
                           Eclusters.last(),
                           2
                       )



                       val diagnosis = ArrayList<String>()
                       for ((index, value) in overalls.withIndex()) {
                           diagnosis.add(
                               Scorer.scoring(
                                   overalls[index],
                                   Bclusters[index],
                                   Cclusters[index],
                                   Dclusters[index],
                                   Eclusters[index],
                                   1
                               )
                           )

                       }

                       val colors = ArrayList<Int>()
                       for (i in diagnosis.indices) {
                           if (diagnosis[i] == "LOW") {
                               colors.add(Color.rgb(2, 178, 29))
                           } else if (diagnosis[i] == "MEDIUM") {
                               colors.add(Color.rgb(215, 216, 73))
                           } else {
                               colors.add(Color.rgb(210, 4, 45))
                           }
                       }


                       barEntriesList = ArrayList()
                       for ((index, value) in overalls.withIndex()) {
                           barEntriesList.add(BarEntry((index).toFloat(), value.toFloat()))

                       }

                       val date = arrayListOf<String>()
                       for ((index, value) in dateList.withIndex()) {
                           date.add(value)
                       }

                       val xAxis = barChart.xAxis
                       xAxis.setDrawLabels(true)
                       xAxis.position = XAxis.XAxisPosition.BOTTOM
                       xAxis.granularity = 1f
                       xAxis.setCenterAxisLabels(true)
                       xAxis.isGranularityEnabled = false
                       xAxis.setAvoidFirstLastClipping(true)
                       barChart.xAxis.valueFormatter = IndexAxisValueFormatter(date)


                       barChart.setVisibleXRangeMaximum(5f)
                       barChart.isDragEnabled = true
                       barChart.isScaleXEnabled = true
                       barChart.setDrawGridBackground(false)
                       barDataSet = BarDataSet(barEntriesList, "Ukupni rezultati testa")
                       barDataSet.colors = colors
                       barData = BarData(barDataSet)
                       barChart.data = barData
                       barData.barWidth = 0.5f
                       barChart.setFitBars(true)
                       barDataSet.valueTextColor = Color.BLACK
                       barDataSet.valueTextSize = 16f
                       barChart.description.isEnabled = false
                       barChart.setPinchZoom(true)
                       barChart.setDrawBarShadow(false)

                       val leftAxis: YAxis = barChart.axisLeft
                       leftAxis.setDrawGridLines(false)
                       leftAxis.spaceTop = 35f
                       leftAxis.axisMinimum = 0f
                       leftAxis.axisMaximum = 80f

                       barChart.axisRight.isEnabled = false
                       barChart.axisLeft.setDrawGridLines(false)
                       barChart.xAxis.setDrawGridLines(false)
                       barChart.invalidate()

                   }
                   else {
                       val barData = BarData()
                       barChart.data = barData
                       barChart.invalidate()
                       bklaster.text = "Nepoznato"
                       cklaster.text = "Nepoznato"
                       dklaster.text = "Nepoznato"
                       eklaster.text = "Nepoznato"
                       ukupno.text = "Nepoznato"
                       dijagnoza.text= "Nepoznato"
                       dateTXT.text ="Nepoznato"
                       Toast.makeText(context, "Za prikaz potrebno riješiti test", Toast.LENGTH_SHORT).show()
                   }

               }
       }






        return view
    }

}