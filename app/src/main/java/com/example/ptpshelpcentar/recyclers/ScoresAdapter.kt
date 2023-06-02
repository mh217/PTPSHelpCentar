package com.example.ptpshelpcentar.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ptpshelpcentar.R
import com.example.ptpshelpcentar.data.Detailed

class ScoresAdapter (private val detaileList : ArrayList<Detailed>) : RecyclerView.Adapter<ScoresAdapter.DetailesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresAdapter.DetailesViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerfordetailes, parent, false)
        return DetailesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailesViewHolder, position: Int) {
        when(holder) {
            is DetailesViewHolder -> {
                holder.bind(position, detaileList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return  detaileList.size
    }

    class DetailesViewHolder(val view: View):RecyclerView.ViewHolder(view) {

        val score = view.findViewById<TextView>(R.id.ScoreTxt)
        val severtiy = view.findViewById<TextView>(R.id.severityTxt)
        val alterations = view.findViewById<TextView>(R.id.altTxt)
        val avoidance = view.findViewById<TextView>(R.id.avoidTxt)
        val hyperarousal = view.findViewById<TextView>(R.id.hyperTxt)
        val reexperincing = view.findViewById<TextView>(R.id.reexTxt)
        val dates = view.findViewById<TextView>(R.id.dateTxt)



        fun bind(index: Int,  detailes : Detailed) {
            score.text = detailes.Scores.toString()
            severtiy.text =detailes.Severity.toString()
            alterations.text =detailes.Alterations.toString()
            avoidance.text = detailes.Avoidance.toString()
            hyperarousal.text = detailes.Hyperarousal.toString()
            reexperincing.text = detailes.Reexperiencing.toString()
            dates.text = detailes.Dates.toString()
        }

    }

}