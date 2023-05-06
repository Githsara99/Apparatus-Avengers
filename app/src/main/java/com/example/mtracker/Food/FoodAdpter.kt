package com.example.mtracker.Food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.FoodModel
import com.example.mtracker.R

class FoodAdpter(private val foodList: ArrayList<FoodModel>) :
    RecyclerView.Adapter<FoodAdpter.ViewHolder>() {


    private lateinit var foodamount: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        foodamount = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_food_list,parent,false)
        return ViewHolder(itemView, foodamount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = foodList[position]
        holder.foodamount.text = currentEmp.bills
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val foodamount : TextView = itemView.findViewById(R.id.foodamount)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

}