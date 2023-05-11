package com.example.mtracker.Food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Bill.BillAdapter
import com.example.mtracker.Bill.Edit_bill
import com.example.mtracker.Models.BillModel
import com.example.mtracker.Models.FoodModel
import com.example.mtracker.R
import com.google.firebase.database.*

class FoodFetch : AppCompatActivity() {
    private lateinit var foodRecyclerView : RecyclerView
    private lateinit var rvFoodData : TextView
    private lateinit var foodList : ArrayList<FoodModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_fetch)

        foodRecyclerView = findViewById(R.id.rvFood)
        foodRecyclerView.layoutManager = LinearLayoutManager(this)
        foodRecyclerView.setHasFixedSize(true)
        rvFoodData = findViewById(R.id.rvFoodData)

        foodList = arrayListOf<FoodModel>()

        getFoodData()
    }

    private fun getFoodData() {
        foodRecyclerView.visibility = View.GONE
        rvFoodData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Food")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(FoodModel::class.java)
                        foodList.add(empData!!)
                    }
                    val mAdapter = FoodAdpter(foodList)
                    foodRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : FoodAdpter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FoodFetch, Edit_food::class.java)

                            //put extras
                            intent.putExtra("billId",foodList[position].billId)
                            intent.putExtra("bills",foodList[position].bills)

                            startActivity(intent)
                        }

                    })

                    foodRecyclerView.visibility = View.VISIBLE
                    rvFoodData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}