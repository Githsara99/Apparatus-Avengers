package com.example.mtracker.Bill

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtracker.Models.BillModel
import com.example.mtracker.R
import com.google.firebase.database.*

class BillFetch : AppCompatActivity() {
    private lateinit var billRecyclerView : RecyclerView
    private lateinit var rvBillData : TextView
    private lateinit var billList : ArrayList<BillModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_fetch)

        billRecyclerView = findViewById(R.id.rvBill)
        billRecyclerView.layoutManager = LinearLayoutManager(this)
        billRecyclerView.setHasFixedSize(true)
        rvBillData = findViewById(R.id.rvBillData)

        billList = arrayListOf<BillModel>()

        getBillData()
    }

    private fun getBillData() {
        billRecyclerView.visibility = View.GONE
        rvBillData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Bill")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                billList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val empData = empSnap.getValue(BillModel::class.java)
                        billList.add(empData!!)
                    }
                    val mAdapter = BillAdapter(billList)
                    billRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : BillAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@BillFetch, Edit_bill::class.java)

                            //put extras
                            intent.putExtra("billId",billList[position].billId)
                            intent.putExtra("bills",billList[position].bills)

                            startActivity(intent)
                        }

                    })

                    billRecyclerView.visibility = View.VISIBLE
                    rvBillData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}