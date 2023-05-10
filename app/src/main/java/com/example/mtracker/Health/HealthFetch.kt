package com.example.mtracker.Health

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
import com.example.mtracker.Models.HealthModel
import com.example.mtracker.R
import com.google.firebase.database.*

class HealthFetch : AppCompatActivity() {
    private lateinit var healthRecyclerView : RecyclerView
    private lateinit var rvHealthData : TextView
    private lateinit var healthList : ArrayList<HealthModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_fetch)

        healthRecyclerView = findViewById(R.id.rvHealth)
        healthRecyclerView.layoutManager = LinearLayoutManager(this)
        healthRecyclerView.setHasFixedSize(true)
        rvHealthData = findViewById(R.id.rvHealthData)

        healthList = arrayListOf<HealthModel>()

        getBillData()
    }

    private fun getBillData() {
        healthRecyclerView.visibility = View.GONE
        rvHealthData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Health")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                healthList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(HealthModel::class.java)
                        healthList.add(empData!!)
                    }
                    val mAdapter = HealthAdapter(healthList)
                    healthRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : HealthAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@HealthFetch, Edit_health::class.java)

                            //put extras
                            intent.putExtra("billId",healthList[position].billId)
                            intent.putExtra("bills",healthList[position].bills)

                            startActivity(intent)
                        }

                    })

                    healthRecyclerView.visibility = View.VISIBLE
                    rvHealthData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}