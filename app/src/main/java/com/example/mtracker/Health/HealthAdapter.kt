package com.example.mtracker.Health

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.HealthModel
import com.example.mtracker.R

class HealthAdapter(private val healthList: ArrayList<HealthModel>) :
    RecyclerView.Adapter<HealthAdapter.ViewHolder>() {


    private lateinit var healthamount: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        healthamount = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_bill_list,parent,false)
        return ViewHolder(itemView, healthamount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = healthList[position]
        holder.healthamount.text = currentEmp.bills
    }

    override fun getItemCount(): Int {
        return healthList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val healthamount : TextView = itemView.findViewById(R.id.billamount)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}