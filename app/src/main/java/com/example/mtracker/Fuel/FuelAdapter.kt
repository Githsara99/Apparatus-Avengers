package com.example.mtracker.Fuel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.FuelModel
import com.example.mtracker.R

class FuelAdapter(private val feulList: ArrayList<FuelModel>) :
    RecyclerView.Adapter<FuelAdapter.ViewHolder>() {


    private lateinit var fuelamount: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        fuelamount = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_fuel_list,parent,false)
        return ViewHolder(itemView, fuelamount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = feulList[position]
        holder.fuelamount.text = currentEmp.bills
    }

    override fun getItemCount(): Int {
        return feulList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val fuelamount : TextView = itemView.findViewById(R.id.fuelamount)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}