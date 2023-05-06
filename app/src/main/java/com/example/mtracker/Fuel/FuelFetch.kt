package com.example.mtracker.Fuel

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
import com.example.mtracker.Models.FuelModel
import com.example.mtracker.R
import com.google.firebase.database.*

class FuelFetch : AppCompatActivity() {
    private lateinit var fuelRecyclerView : RecyclerView
    private lateinit var rvFuelData : TextView
    private lateinit var fuelList : ArrayList<FuelModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_fetch)

        fuelRecyclerView = findViewById(R.id.rvFuel)
        fuelRecyclerView.layoutManager = LinearLayoutManager(this)
        fuelRecyclerView.setHasFixedSize(true)
        rvFuelData = findViewById(R.id.rvFuelData)

        fuelList = arrayListOf<FuelModel>()

        getBillData()
    }

    private fun getBillData() {
        fuelRecyclerView.visibility = View.GONE
        rvFuelData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Fuel")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fuelList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(FuelModel::class.java)
                        fuelList.add(empData!!)
                    }
                    val mAdapter = FuelAdapter(fuelList)
                    fuelRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : FuelAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FuelFetch, Edit_fuel::class.java)

                            //put extras
                            intent.putExtra("billId",fuelList[position].billId)
                            intent.putExtra("bills",fuelList[position].bills)

                            startActivity(intent)
                        }

                    })

                    fuelRecyclerView.visibility = View.VISIBLE
                    rvFuelData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}