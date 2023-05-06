package com.example.mtracker.Education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.EduModel
import com.example.mtracker.R

class EduAdapter(private val eduList: ArrayList<EduModel>) :
    RecyclerView.Adapter<EduAdapter.ViewHolder>() {


    private lateinit var eduamount: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        eduamount = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_edu_list,parent,false)
        return ViewHolder(itemView, eduamount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = eduList[position]
        holder.eduamount.text = currentEmp.bills
    }

    override fun getItemCount(): Int {
        return eduList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val eduamount : TextView = itemView.findViewById(R.id.eduamount)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}