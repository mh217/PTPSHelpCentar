package com.example.ptpshelpcentar.recyclers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ptpshelpcentar.R
import com.example.ptpshelpcentar.TreatmentDetails
import com.example.ptpshelpcentar.data.Treatment

class TreatmentAdapter(private val treatmentList: ArrayList<Treatment>) : RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclertreatment, parent, false)
        return TreatmentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TreatmentViewHolder, position: Int) {
        when(holder) {
            is TreatmentViewHolder -> {
                holder.bind(position, treatmentList[position])
            }
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, TreatmentDetails::class.java)
            intent.putExtra("treatmentID", treatmentList[position].Id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  treatmentList.size
    }



    class TreatmentViewHolder(val view: View):RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.NametreatTxt)
        val image = view.findViewById<ImageView>(R.id.Imageim)
        val simptomi = view.findViewById<TextView>(R.id.treatarrayTxt)


        fun bind(index: Int,  treatment: Treatment) {
            Glide
                .with(view.context)
                .load(treatment.Image)
                .into(image)
            name.text=treatment.Name.toString()
            simptomi.text = treatment.Clusters.toString()


        }

    }

}